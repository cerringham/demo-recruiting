package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.ExpertiseDto;
import it.proactivity.recruiting.service.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public class ExpertiseController {

    @Autowired
    ExpertiseService expertiseService;

    @GetMapping("/get-all-expertise")
    public ResponseEntity<List<ExpertiseDto>> getAll(@RequestHeader("Token") String accessToken) {
        return expertiseService.getAll(accessToken);
    }

    @GetMapping("/get-expertise/{id}")
    public ResponseEntity<ExpertiseDto> findById(@PathVariable Long id, @RequestHeader("Token") String accessToken) {
        return expertiseService.findById(id, accessToken);
    }
}
