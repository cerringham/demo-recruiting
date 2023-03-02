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
import org.apache.commons.lang3.text.WordUtils;
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
    CurriculumRepository curriculumRepository;


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
        if (dto == null) {
            throw new IllegalArgumentException("dto can't be null");
        }
        Set<String> skills = dto.getSkillLevelMap().keySet();
        candidateValidator.validateSkill(skills);

        //Creo una mappa con le chiavi in lowerCase per un matching delle skill nel db
        Map<String, Level> lowerCaseKeySkillLevelMap = createSkillNameLowerLevelMap(dto.getSkillLevelMap());

        globalValidator.validateStringAlphaSpace(dto.getName());
        globalValidator.validateStringAlphaSpace(dto.getSurname());
        globalValidator.validateEmail(dto.getEmail());
        globalValidator.validateAge(dto.getBirthDate());
        globalValidator.validatePhoneNumber(dto.getPhoneNumber());

        //Creo il candidate e inserisco le skill che non sono presenti nel db
        Candidate candidate = createCandidate(dto);

        if (candidate == null) {
            throw new IllegalStateException("Cannot create candidate");
        }

        //cerco tutte le skill dopo la creazione del candidate
        Set<Skill> realSkills = skillRepository.findByNameIgnoreCaseIn(skills);

        //creazione lista di cv associate al candidate
        List<Curriculum> curriculumList = createCurriculumList(realSkills, lowerCaseKeySkillLevelMap, candidate);
        candidate.setCandidateSkillList(curriculumList);
        candidateRepository.save(candidate);

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

        if (dto == null) {
            throw new IllegalArgumentException("dto can't be null");
        }

        Optional<Candidate> candidate = candidateRepository.findByIdAndIsActive(dto.getId(), true);
        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LocalDate parsedDate = parsingUtility.parseStringToLocalDate(dto.getBirthDate());
        if (parsedDate == null) {
            throw new IllegalArgumentException("Impossible to parse the date");
        }
        setAllStringParametersForCandidate(dto, candidate.get());
        candidate.get().setBirthDate(parsedDate);

        Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCase(dto.getExpertise());
        if (expertise.isEmpty()) {
            throw new IllegalArgumentException("Expertise not found");
        }
        candidate.get().setExpertise(expertise.get());

        //Inserimento di eventuali nuove skill
        insertSkills(dto.getSkillLevelMap());

        Set<String> dtoSkills = dto.getSkillLevelMap().keySet();
        Map<String, Level> lowerCaseKeySkillLevelMap = createSkillNameLowerLevelMap(dto.getSkillLevelMap());

        //recuper delle skill reali del candidate
        Set<Skill> realSkills = skillRepository.findByNameIgnoreCaseIn(dtoSkills);

        //creazione  lista di cv
        List<Curriculum> curriculumList = createCurriculumList(realSkills, lowerCaseKeySkillLevelMap, candidate.get());

        candidate.get().setCandidateSkillList(curriculumList);
        candidateRepository.save(candidate.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private void setAllStringParametersForCandidate(CandidateInformationDto dto, Candidate candidate) {
        candidate.setName(dto.getName());
        candidate.setSurname(dto.getSurname());
        candidate.setPhoneNumber(dto.getPhoneNumber());
        candidate.setGender(dto.getGender());
        candidate.setEmail(dto.getEmail());
        candidate.setRegionOfResidence(dto.getRegionOfResidence());
        candidate.setCountryOfBirth(dto.getCountryOfBirth());
        candidate.setStreetOfResidence(dto.getStreetOfResidence());
        candidate.setCityOfBirth(dto.getCityOfBirth());
        candidate.setCityOfResidence(dto.getCityOfResidence());
        candidate.setCountryOfResidence(dto.getCountryOfResidence());
        candidate.setFiscalCode(dto.getFiscalCode());
    }

    private Candidate createCandidate(CandidateInformationDto dto) {
        Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCase(dto.getExpertise());
        if (expertise.isEmpty()) {
            throw new IllegalArgumentException("Expertise doesn't exists");
        }

        //salvataggio ed inserimento delle skill
        insertSkills(dto.getSkillLevelMap());

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

    private void insertSkills(Map<String, Level> skillLevelMap) {
        Set<String> dtoSkills = skillLevelMap.keySet();

        Set<Skill> skills = skillRepository.findByNameIgnoreCaseIn(dtoSkills);
        //salvataggio delle skill non presenti nel db
        if (skills.isEmpty()) {
            dtoSkills.stream().forEach(s -> {
                Skill skill = SkillBuilder.newBuilder(WordUtils.capitalizeFully(s)).isActive(true).build();
                skillRepository.save(skill);
            });
        }

        //controllo eventuale che nella mappa ci siano skill esistenti e non
        if ((!skills.isEmpty()) && skills.size() != dtoSkills.size()) {
            Set<String> existingSkillsName = skills.stream()
                    .map(Skill::getName).collect(Collectors.toSet());

            Set<String> nonexistentSkillsName = dtoSkills.stream()
                    .filter(s -> predicateUtility.filterSkillsName(existingSkillsName, s))
                    .collect(Collectors.toSet());

            nonexistentSkillsName.stream()
                    .forEach(s -> {
                        String correctSkillName = WordUtils.capitalizeFully(s);
                        Skill skill = SkillBuilder.newBuilder(correctSkillName).isActive(true).build();
                        skillRepository.save(skill);
                    });
        }
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

    private Map<String, Level> createSkillNameLowerLevelMap(Map<String, Level> skillLevelMap) {
        Map<String, Level> lowerCaseKeySkillLevelMap = new HashMap<>();
        Set<String> skills = skillLevelMap.keySet();

        skills.stream().forEach(s -> {
            Level level = skillLevelMap.get(s);
            lowerCaseKeySkillLevelMap.put(s.toLowerCase(), level);
        });
        return lowerCaseKeySkillLevelMap;
    }

    private List<Curriculum> createCurriculumList(Set<Skill> skills, Map<String, Level> lowerCaseKeySkillLevelMap,
                                                  Candidate candidate) {
        return skills.stream()
                .map(s -> createCurriculum(candidate, s,
                        lowerCaseKeySkillLevelMap.get(s.getName().toLowerCase()), true)).toList();
    }
}

