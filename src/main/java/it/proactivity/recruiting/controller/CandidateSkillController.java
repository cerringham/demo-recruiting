package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CandidateSkillDto;
import it.proactivity.recruiting.service.CandidateSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CandidateSkillController {

    @Autowired
    CandidateSkillService candidateSkillService;

    @GetMapping("/find-all-candidateSkills")
    public ResponseEntity<List<CandidateSkillDto>> findAll() {
        return candidateSkillService.findAll();
    }

    @GetMapping("/find-candidateSkill/{id}")
    public ResponseEntity<CandidateSkillDto> findById(@PathVariable Long id) {
        return candidateSkillService.findById(id);
    }
}
