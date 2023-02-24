package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.ExpertiseDto;
import it.proactivity.recruiting.service.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ExpertiseController {

    @Autowired
    ExpertiseService expertiseService;

    @GetMapping("/get-all-expertise")
    public ResponseEntity<List<ExpertiseDto>> getAll() {
        return expertiseService.getAll();
    }

    @GetMapping("/get-expertise/{id}")
    public ResponseEntity<ExpertiseDto> getById(@PathVariable Long id) {
        return expertiseService.getById(id);
    }
}
