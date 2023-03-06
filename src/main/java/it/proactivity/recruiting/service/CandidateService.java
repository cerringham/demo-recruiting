package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.CandidateWithSkillDtoBuilder;
import it.proactivity.recruiting.builder.CurriculumBuilder;
import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateWithSkillDto;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import it.proactivity.recruiting.repository.SkillRepository;
import it.proactivity.recruiting.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    CandidateValidator candidateValidator;

    @Autowired
    CandidateUtility candidateUtility;

    @Autowired
    ExpertiseRepository expertiseRepository;
    @Autowired
    SkillValidator skillValidator;

    @Autowired
    ExpertiseUtility expertiseUtility;

    @Autowired
    CurriculumUtility curriculumUtility;

    public ResponseEntity<Set<CandidateDto>> getAll() {

        List<Candidate> candidateList = candidateRepository.findByIsActive(true);

        Set<CandidateDto> dtoList = candidateList.stream()
                .map(c -> candidateUtility.createCandidateDto(c))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CandidateDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<Candidate> candidate = candidateRepository.findByIdAndIsActive(id, true);

        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(candidateUtility.createCandidateDto(candidate.get()));
    }

    public ResponseEntity<CandidateWithSkillDto> insertNewCandidate(CandidateWithSkillDto candidateWithSkillDto) {
        if (!candidateValidator.validateCandidate(candidateWithSkillDto)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Expertise> expertise = expertiseRepository.findByNameAndIsActive(candidateWithSkillDto.getExpertise().getName(), true);
        if (expertise.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<String> curriculumNameList = candidateWithSkillDto.getCurriculumList().stream()
                .map(c -> c.getSkillDto().getName())
                .toList();
        List<Skill> skillsList = skillValidator.createSkillList(curriculumNameList);
        Candidate candidate = candidateUtility.createCandidateFromCandidateWithSkillDto(candidateWithSkillDto);
        Set<Curriculum> curriculumList = skillsList.stream().map(s -> CurriculumBuilder.newBuilder(candidate)
                .skill(s)
                .isActive(true)
                .build())
                .collect(Collectors.toSet());
        candidate.setCandidateSkillList(curriculumList);
        candidateRepository.save(candidate);
        return ResponseEntity.ok(candidateWithSkillDto);
    }

    public ResponseEntity<CandidateWithSkillDto> updateCandidate(CandidateWithSkillDto candidateWithSkillDto) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateWithSkillDto.getId());
        if (candidate.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Expertise> expertise = expertiseRepository.findByNameAndIsActive(candidateWithSkillDto.getExpertise().getName(), true);
        if (candidate.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<String> curriculumNameList = candidateWithSkillDto.getCurriculumList().stream()
                .map(c -> c.getSkillDto().getName())
                .toList();
        List<Skill> skillList = skillValidator.createSkillList(curriculumNameList);
        Set<Curriculum> curriculumSet = skillList.stream().map( s -> CurriculumBuilder.newBuilder(candidate.get())
                        .skill(s)
                        .isActive(true)
                        .build())
                .collect(Collectors.toSet());
        candidate.get().setCityOfResidence(candidateWithSkillDto.getCityOfResidence());
        candidate.get().setStreetOfResidence(candidateWithSkillDto.getStreetOfResidence());
        candidate.get().setRegionOfResidence(candidateWithSkillDto.getRegionOfResidence());
        candidate.get().setCountryOfResidence(candidateWithSkillDto.getCountryOfResidence());
        candidate.get().setEmail(candidateWithSkillDto.getEmail());
        candidate.get().setPhoneNumber(candidateWithSkillDto.getPhoneNumber());
        candidate.get().setGender(candidateWithSkillDto.getGender());
        candidate.get().setIsActive(candidateWithSkillDto.getIsActive());
        candidate.get().setExpertise(expertise.get());
        candidate.get().setCandidateSkillList(curriculumSet);
        candidateRepository.save(candidate.get());
        return ResponseEntity.ok(candidateWithSkillDto);
    }

    public ResponseEntity<CandidateDto> deleteById(Long id) {
        globalValidator.validateId(id);
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        candidate.get().setIsActive(false);
        candidateRepository.save(candidate.get());
        CandidateDto candidateDto = candidateUtility.createCandidateDto(candidate.get());
        return ResponseEntity.ok(candidateDto);
    }
}
