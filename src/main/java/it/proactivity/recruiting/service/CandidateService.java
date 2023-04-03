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

    public ResponseEntity insertCandidate(CandidateInformationDto dto, String accessToken) {

        if (!candidateUtility.verifyTokenForInsertCandidate(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!candidateValidator.validateCandidateInformationDtoParameters(dto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Map<Skill, Level> skillLevelMap = candidateUtility.insertNewSkillsAndReturnSkillLevelMap(dto.getSkillLevelMap());

        Optional<Candidate> candidate = candidateUtility.createCandidate(dto);
        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Curriculum> curriculumList = candidateUtility.createCurriculumList(skillLevelMap, candidate.get());
        candidate.get().setCandidateSkillList(curriculumList);
        candidateRepository.save(candidate.get());

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

        candidateUtility.setAllStringParametersForCandidate(dto, candidate.get());

        candidate.get().setBirthDate(parsedDate);

        Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCaseAndIsActive(dto.getExpertise(), true);
        if (expertise.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        candidate.get().setExpertise(expertise.get());

        Map<Skill, Level> skillLevelMap = candidateUtility.insertNewSkillsAndReturnSkillLevelMap(dto.getSkillLevelMap());

        List<Curriculum> curriculumList = candidateUtility.createCurriculumList(skillLevelMap, candidate.get());

        candidate.get().setCandidateSkillList(curriculumList);
        candidateRepository.save(candidate.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

