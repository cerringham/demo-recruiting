package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateInformationDto;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import it.proactivity.recruiting.utility.*;
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
    CandidateUtility candidateUtility;

    @Autowired
    AccessTokenUtility accessTokenUtility;


    public ResponseEntity<Set<CandidateDto>> getAll() {

        List<Candidate> candidateList = candidateRepository.findByIsActive(true);

        Set<CandidateDto> dtoList = candidateList.stream()
                .map(c -> candidateUtility.createCandidateDto(c.getFiscalCode(), c.getName(), c.getSurname(), c.getCityOfBirth(),
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
        return ResponseEntity.ok(candidateUtility.createCandidateDto(candidate.get().getFiscalCode(), candidate.get().getName(),
                candidate.get().getSurname(), candidate.get().getCityOfBirth(), candidate.get().getCountryOfBirth(),
                candidate.get().getCityOfResidence(), candidate.get().getStreetOfResidence(),
                candidate.get().getRegionOfResidence(), candidate.get().getCountryOfResidence(),
                candidate.get().getEmail(), candidate.get().getPhoneNumber(), candidate.get().getGender(),
                candidate.get().getIsActive(), parsingUtility.parseDateToString(candidate.get().getBirthDate())));
    }

    public ResponseEntity insertCandidate(String accessToken, CandidateInformationDto dto) {
        if (!accessTokenUtility.checkIfTokenIsActive(accessToken) &&
                !accessTokenUtility.checkIfTokenBelongsToRequiredAccount(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Set<String> skills = dto.getSkillLevelMap().keySet();
        if (Boolean.FALSE.equals(candidateValidator.validateSkill(skills))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The candidate must have at least one skill");
        }
        if (!globalValidator.validateStringAlphaSpace(dto.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The candidate name must be alphaSpace");
        }

        if (!globalValidator.validateStringAlphaSpace(dto.getSurname())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The candidate surname must be alphaSpace");
        }

        if (!globalValidator.validateEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The candidate email format is wrong");
        }

        if (!globalValidator.validateAge(dto.getBirthDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The candidate is to young");
        }

        if (!globalValidator.validatePhoneNumber(dto.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The candidate phoneNumber is wrong");
        }

        //salvataggio ed inserimento delle nuove skill e creazione mappa skill level
        Map<Skill, Level> skillLevelMap = candidateUtility.insertNewSkillsAndReturnSkillLevelMap(dto.getSkillLevelMap());

        //Creo il candidate e inserisco le skill che non sono presenti nel db
        Candidate candidate;
        try {
            candidate = candidateUtility.createCandidate(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        //creazione lista di cv associate al candidate
        List<Curriculum> curriculumList = candidateUtility.createCurriculumList(skillLevelMap, candidate);
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Candidate> candidate = candidateRepository.findByIdAndIsActive(dto.getId(), true);
        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LocalDate parsedDate = parsingUtility.parseStringToLocalDate(dto.getBirthDate());
        if (parsedDate == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Impossible to parse the date");
        }
        //Setto tutti gli attributi di tipo stringa per il candidate
        candidateUtility.setAllStringParametersForCandidate(dto, candidate.get());

        candidate.get().setBirthDate(parsedDate);

        Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCaseAndIsActive(dto.getExpertise(), true);
        if (expertise.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        candidate.get().setExpertise(expertise.get());

        Map<Skill, Level> skillLevelMap = candidateUtility.insertNewSkillsAndReturnSkillLevelMap(dto.getSkillLevelMap());

        //creazione  lista di cv
        List<Curriculum> curriculumList = candidateUtility.createCurriculumList(skillLevelMap, candidate.get());

        candidate.get().setCandidateSkillList(curriculumList);
        candidateRepository.save(candidate.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

