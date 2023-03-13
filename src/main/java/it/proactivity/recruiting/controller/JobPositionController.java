package it.proactivity.recruiting.controller;


import it.proactivity.recruiting.model.dto.JobPositionInsertionDto;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<JobPositionDto> findById(@PathVariable Long id) {
        return jobPositionService.findById(id);
    }

    @PostMapping("/insert-job-position")
    public ResponseEntity insertJobPosition(@RequestBody JobPositionInsertionDto dto) {

        return jobPositionService.insertJobPosition(dto);
    }
}
