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
    public ResponseEntity<Set<CandidateDto>> getAll(@RequestHeader("Token") String accessToken) {
        return candidateService.getAll(accessToken);
    }

    @GetMapping("/get-candidate/{id}")
    public ResponseEntity<CandidateDto> findById(@RequestHeader("Token") String accessToken, @PathVariable Long id) {
        return candidateService.findById(id, accessToken);
    }

    @PostMapping("/insert-candidate")
    public ResponseEntity insertCandidate(@RequestHeader("Token") String accessToken, @RequestBody CandidateInformationDto dto) {
        return candidateService.insertCandidate(dto, accessToken);
    }

    @GetMapping("/delete-candidate/{id}")
    public ResponseEntity deleteCandidateById(@RequestHeader("Token") String accessToken, @PathVariable Long id) {
        return candidateService.deleteCandidateById(id, accessToken);
    }

    @PostMapping("update-candidate")
    public ResponseEntity updateCandidate(@RequestHeader("Token") String accessToken, @RequestBody CandidateInformationDto dto) {
        return candidateService.updateCandidate(dto, accessToken);
    }
}
