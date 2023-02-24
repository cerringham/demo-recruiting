package it.proactivity.recruiting.controller;


import it.proactivity.recruiting.model.dto.JobPositionStatusDto;
import it.proactivity.recruiting.service.JobPositionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobPositionStatusController {

    @Autowired
    JobPositionStatusService jobPositionStatusService;

    @GetMapping("/get-all-JobPositionStatus")
    public ResponseEntity<List<JobPositionStatusDto>> getAll() {
        return jobPositionStatusService.getAll();
    }

    @GetMapping("/get-JobPositionStatus/{id}")
    public ResponseEntity<JobPositionStatusDto> findById(@PathVariable Long id) {
        return jobPositionStatusService.findById(id);
    }
}
