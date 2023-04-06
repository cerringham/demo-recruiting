package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateInformationDto;
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

    @PostMapping("/insert-candidate")
    public ResponseEntity insertCandidate(@RequestHeader("Token") String accessToken ,@RequestBody CandidateInformationDto dto) {
        return candidateService.insertCandidate(accessToken, dto);
    }

    @GetMapping("/delete-candidate/{id}")
    public ResponseEntity deleteCandidateById(@PathVariable Long id) {
        return candidateService.deleteCandidateById(id);
    }

    @PostMapping("update-candidate")
    public ResponseEntity updateCandidate(@RequestBody CandidateInformationDto dto) {
        return candidateService.updateCandidate(dto);
    }
}
