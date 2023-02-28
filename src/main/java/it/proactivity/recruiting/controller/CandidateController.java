package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @GetMapping("/get-all-candidates")
    public ResponseEntity<Set<CandidateDto>> getAll() {
        return candidateService.getAll();
    }

    @GetMapping("/get-candidate/{id}")
    public ResponseEntity<CandidateDto> findById(@PathVariable Long id) {
        return candidateService.findById(id);
    }

    @PostMapping("/delete-candidate")
    public ResponseEntity<CandidateDto> deleteById(@RequestParam Long id) {
        return candidateService.deleteById(id);
    }

    @PostMapping("/insert-candidate")
    public ResponseEntity<CandidateDto> insertNewCandidate(@RequestBody CandidateDto candidateDto) {
        return candidateService.insertNewCandidate(candidateDto);
    }
}
