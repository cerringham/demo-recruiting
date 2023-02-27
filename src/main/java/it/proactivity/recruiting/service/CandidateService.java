package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.CandidateBuilder;
import it.proactivity.recruiting.builder.CandidateDtoBuilder;
import it.proactivity.recruiting.builder.CurriculumBuilder;
import it.proactivity.recruiting.builder.SkillBuilder;
import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateInformationDto;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.repository.CurriculumRepository;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import it.proactivity.recruiting.repository.SkillRepository;
import it.proactivity.recruiting.utility.CandidateValidator;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.ParsingUtility;
import it.proactivity.recruiting.utility.PredicateUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ParsingUtility parsingUtility;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    CandidateValidator candidateValidator;

    @Autowired
    ExpertiseRepository expertiseRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    PredicateUtility predicateUtility;
    @Autowired
    private CurriculumRepository curriculumRepository;


    public ResponseEntity<Set<CandidateDto>> getAll() {

        List<Candidate> candidateList = candidateRepository.findByIsActive(true);

        Set<CandidateDto> dtoList = candidateList.stream()
                .map(c -> createCandidateDto(c.getFiscalCode(), c.getName(), c.getSurname(), c.getCityOfBirth(),
                        c.getCountryOfBirth(), c.getCityOfResidence(), c.getStreetOfResidence(), c.getRegionOfResidence(),
                        c.getCountryOfResidence(), c.getEmail(), c.getPhoneNumber(), c.getGender(), c.getIsActive(),
                        parsingUtility.parseDateToString(c.getBirthDate())))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CandidateDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<Candidate> candidate = candidateRepository.findByIdAndIsActive(id, true);

        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(createCandidateDto(candidate.get().getFiscalCode(), candidate.get().getName(),
                candidate.get().getSurname(), candidate.get().getCityOfBirth(), candidate.get().getCountryOfBirth(),
                candidate.get().getCityOfResidence(), candidate.get().getStreetOfResidence(),
                candidate.get().getRegionOfResidence(), candidate.get().getCountryOfResidence(),
                candidate.get().getEmail(), candidate.get().getPhoneNumber(), candidate.get().getGender(),
                candidate.get().getIsActive(), parsingUtility.parseDateToString(candidate.get().getBirthDate())));
    }

    public ResponseEntity insertCandidate(CandidateInformationDto dto) {
        candidateValidator.validateSkillLevelMap(dto.getSkillLevelMap());
        Set<String> skills = dto.getSkillLevelMap().keySet();
        candidateValidator.validateSkill(skills);

        Map<String, Level> lowerCaseKeySkillLevelMap = new HashMap<>();

        skills.stream().forEach(s -> {
            Level level = dto.getSkillLevelMap().get(s);
            lowerCaseKeySkillLevelMap.put(s.toLowerCase(), level);
        });

        globalValidator.validateNameAndSurname(dto.getName(), dto.getSurname());
        globalValidator.validateEmail(dto.getEmail());
        globalValidator.validateAge(dto.getBirthDate());
        globalValidator.validatePhoneNumber(dto.getPhoneNumber());

        Candidate candidate = createCandidate(dto);

        if (candidate == null) {
            throw new IllegalStateException("Cannot create candidate");
        }
        candidateRepository.save(candidate);

        Set<Skill> realSkills = skillRepository.findByNameIgnoreCaseIn(skills);
        realSkills.stream()
                .forEach(s -> {

                    Curriculum curriculum = createCurriculum(candidate, s,
                            lowerCaseKeySkillLevelMap.get(s.getName().toLowerCase()), true);

                    curriculumRepository.save(curriculum);
                });
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity deleteCandidateById(Long id) {
        globalValidator.validateId(id);

        Optional<Candidate> candidate = candidateRepository.findByIdAndIsActive(id, true);

        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        candidate.get().setIsActive(false);
        candidateRepository.save(candidate.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity updateCandidate(CandidateInformationDto dto) {

        Optional<Candidate> candidate = candidateRepository.findByIdAndIsActive(dto.getId(), true);
        candidateRepository.update

    }

    private Candidate createCandidate(CandidateInformationDto dto) {
        Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCase(dto.getExpertise());
        if (expertise.isEmpty()) {
            throw new IllegalArgumentException("Expertise doesn't exists");
        }
        Set<String> dtoSkills = dto.getSkillLevelMap().keySet();

        Set<Skill>  skills = skillRepository.findByNameIgnoreCaseIn(dtoSkills);
        if (skills.isEmpty()) {
            skills.stream().forEach(s -> {
                String initialLetter = String.valueOf(s.getName().charAt(0));
                s.setName(initialLetter.toUpperCase() + s.getName().substring(1,s.getName().length() +1));
                skillRepository.save(s);
            });
        }

        if ((!skills.isEmpty()) && skills.size() != dtoSkills.size()) {
            Set<String> existingSkillsName = skills.stream()
                            .map(Skill::getName).collect(Collectors.toSet());

          Set<String> nonexistentSkillsName = dtoSkills.stream()
                    .filter(s -> predicateUtility.filterSkillsName(existingSkillsName, s))
                    .collect(Collectors.toSet());

          nonexistentSkillsName.stream()
                  .forEach(s -> {
                      String correctSkillName = s.substring(0,1).toUpperCase() + s.substring(1);
                      Skill skill = SkillBuilder.newBuilder(correctSkillName).isActive(true).build();
                      skillRepository.save(skill);
                  });
        }

        return CandidateBuilder.newBuilder(dto.getName())
                .surname(dto.getSurname())
                .fiscalCode(dto.getFiscalCode())
                .cityOfBirth(dto.getCityOfBirth())
                .countryOfBirth(dto.getCountryOfBirth())
                .cityOfResidence(dto.getCityOfResidence())
                .streetOfResidence(dto.getStreetOfResidence())
                .regionOfResidence(dto.getRegionOfResidence())
                .countryOfResidence(dto.getCountryOfResidence())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .gender(dto.getGender())
                .isActive(true)
                .birthDate(parsingUtility.parseStringToLocalDate(dto.getBirthDate()))
                .expertise(expertise.get())
                .build();
    }

    private CandidateDto createCandidateDto(String fiscalCode, String name, String surname, String cityOfBirth,
                                            String countryOfBirth, String cityOfResidence, String streetOfResidence,
                                            String regionOfResidence, String countryOfResidence, String email,
                                            String phoneNumber, String gender, Boolean isActive, String birthDate) {

        if (StringUtils.isEmpty(fiscalCode) || StringUtils.isEmpty(name) || StringUtils.isEmpty(surname) ||
                StringUtils.isEmpty(cityOfBirth) || StringUtils.isEmpty(countryOfBirth) ||
                StringUtils.isEmpty(cityOfResidence) || StringUtils.isEmpty(streetOfResidence) ||
                StringUtils.isEmpty(regionOfResidence) || StringUtils.isEmpty(countryOfResidence) ||
                StringUtils.isEmpty(email) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(gender) ||
                isActive == null || StringUtils.isEmpty(birthDate)) {

            throw new IllegalArgumentException("The data's for creating the candidate dto are empty or null");
        }

        return CandidateDtoBuilder.newBuilder(name)
                .fiscalCode(fiscalCode)
                .surname(surname)
                .cityOfBirth(cityOfBirth)
                .countryOfBirth(countryOfBirth)
                .cityOfResidence(cityOfResidence)
                .streetOfResidence(streetOfResidence)
                .regionOfResidence(regionOfResidence)
                .countryOfResidence(countryOfResidence)
                .email(email)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .isActive(isActive)
                .birthDate(birthDate)
                .build();
    }

    private Curriculum createCurriculum(Candidate candidate, Skill skill, Level level, Boolean isActive) {
        if (candidate == null || skill == null) {
            throw new IllegalArgumentException("candidate or skill can't be null");
        }

        return CurriculumBuilder.newBuilder(candidate)
                .skill(skill)
                .level(level)
                .isActive(isActive)
                .build();


    }

    private void setCustomerParametersWithoutSkills(CandidateInformationDto dto, Candidate candidate) {
        if (!StringUtils.isEmpty(dto.getName())) {
            candidate.setName(dto.getName());
        }

        if (!StringUtils.isEmpty(dto.getSurname())) {
            candidate.setSurname(dto.getSurname());
        }

        if (!StringUtils.isEmpty(dto.getEmail())) {
            globalValidator.validateEmail(dto.getEmail());
            candidate.setEmail(dto.getEmail());
        }

        if (!StringUtils.isEmpty(dto.getGender())) {
            candidate.setGender(dto.getGender());
        }

        if (!StringUtils.isEmpty(dto.getCityOfBirth())) {
            candidate.setCityOfBirth(dto.getCityOfBirth());
        }

        if (!StringUtils.isEmpty(dto.getCityOfResidence())) {
            candidate.setCityOfResidence(dto.getCityOfResidence());
        }

        if (!StringUtils.isEmpty(dto.getFiscalCode())) {
            candidate.setFiscalCode(dto.getFiscalCode());
        }

        if (!StringUtils.isEmpty(dto.getCountryOfResidence())) {
            candidate.setCountryOfResidence(dto.getCountryOfResidence());
        }

        if (!StringUtils.isEmpty(dto.getStreetOfResidence())) {
            candidate.setStreetOfResidence(dto.getStreetOfResidence());
        }

        if (!StringUtils.isEmpty(dto.getCountryOfBirth())) {
            candidate.setCountryOfBirth(dto.getCountryOfBirth());
        }

        if (!StringUtils.isEmpty(dto.getRegionOfResidence())) {
            candidate.setRegionOfResidence(dto.getRegionOfResidence());
        }

        if (!StringUtils.isEmpty(dto.getPhoneNumber())) {
            globalValidator.validatePhoneNumber(dto.getPhoneNumber());
            candidate.setPhoneNumber(dto.getPhoneNumber());
        }

        if (!StringUtils.isEmpty(dto.getBirthDate())) {
            globalValidator.validateAge(dto.getBirthDate());
            LocalDate parsedDate = parsingUtility.parseStringToLocalDate(dto.getBirthDate());
            candidate.setBirthDate(parsedDate);
        }

        if (!StringUtils.isEmpty(dto.getExpertise())) {
            Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCase(dto.getExpertise());
            if (expertise.isEmpty()) {
                throw new IllegalArgumentException("Expertise not found");
            }
            candidate.setExpertise(expertise.get());
        }

    }
}

