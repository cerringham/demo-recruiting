package it.proactivity.recruiting.controller;


import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobPositionController {

    @Autowired
    JobPositionService jobPositionService;

    @GetMapping("/get-all-JobPosition")
    public ResponseEntity<List<JobPositionDto>> getAll() {
        return jobPositionService.getAll();
    }

    @GetMapping("/get-JobPosition/{id}")
    public ResponseEntity<JobPositionDto> getById(@PathVariable Long id) {
        return jobPositionService.getById(id);
    }
}
