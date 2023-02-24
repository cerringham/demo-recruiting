package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.CandidateSkill;
import it.proactivity.recruiting.model.dto.CandidateSkillDto;
import it.proactivity.recruiting.repository.CandidateSkillRepository;
import it.proactivity.recruiting.utility.CandidateSkillValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateSkillService {

    @Autowired
    CandidateSkillRepository candidateSkillRepository;

    @Autowired
    CandidateSkillValidator candidateSkillValidator;
    public ResponseEntity<List<CandidateSkillDto>> findAll() {

        List<CandidateSkill> candidateSkillList = candidateSkillRepository.findAll();

        List<CandidateSkillDto> dtoList = candidateSkillList.stream()
                .map(cs -> new CandidateSkillDto(cs.getCandidate().getId(), cs.getCandidate().getName(),
                        cs.getCandidate().getSurname(), cs.getSkill().getName()))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CandidateSkillDto> findById(Long id) {
        candidateSkillValidator.validateId(id);

        Optional<CandidateSkill> candidateSkill = candidateSkillRepository.findById(id);

        if (candidateSkill.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(new CandidateSkillDto(candidateSkill.get().getCandidate().getId(),
                candidateSkill.get().getCandidate().getName(), candidateSkill.get().getCandidate().getSurname(),
                candidateSkill.get().getSkill().getName()));
    }
}
