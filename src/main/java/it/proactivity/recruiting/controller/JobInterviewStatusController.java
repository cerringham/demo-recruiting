package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.JobInterviewStatusDto;
import it.proactivity.recruiting.service.JobInterviewStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobInterviewStatusController {
    @Autowired
    JobInterviewStatusService jobInterviewStatusService;

    @GetMapping("/get-all-JobInterviewStatus")
    public ResponseEntity<List<JobInterviewStatusDto>> getAll() {
        return jobInterviewStatusService.getAll();
    }

    @GetMapping("/get-JobInterviewStatus/{id}")
    public ResponseEntity<JobInterviewStatusDto> getById(@PathVariable Long id) {
        return jobInterviewStatusService.getById(id);
    }
}
