package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @GetMapping("/find-all-candidates")
    public ResponseEntity<List<CandidateDto>> findAll() {
        return candidateService.findAll();
    }

    @GetMapping("/find-candidate/{id}")
    public ResponseEntity<CandidateDto> findById(@PathVariable Long id) {
        return candidateService.findById(id);
    }
}
