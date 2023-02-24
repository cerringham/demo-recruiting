package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.service.JobInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobInterviewController {

    @Autowired
    JobInterviewService jobInterviewService;

    @GetMapping("/get-all-JobInterview")
    public ResponseEntity<List<JobInterviewDto>> getAll() {
        return jobInterviewService.getAll();
    }

    @GetMapping("/get-JobInterview/{id}")
    public ResponseEntity<JobInterviewDto> getById(@PathVariable Long id) {
        return jobInterviewService.getById(id);
    }
}
