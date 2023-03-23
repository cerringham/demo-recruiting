package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.model.dto.JobInterviewInsertionDto;
import it.proactivity.recruiting.model.dto.JobInterviewUpdateDto;
import it.proactivity.recruiting.service.JobInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<JobInterviewDto> findById(@PathVariable Long id) {
        return jobInterviewService.findById(id);
    }

    @PostMapping("/create-job-interview")
    public ResponseEntity createJobInterview(@RequestBody JobInterviewInsertionDto dto) {
        return jobInterviewService.createJobInterview(dto);
    }

    @PostMapping("/update-job-interview")
    public ResponseEntity updateJobInterview(@RequestBody JobInterviewUpdateDto dto) {
        return jobInterviewService.updateJobInterview(dto);
    }

    @GetMapping("/delete-job-interview/{id}")
    public ResponseEntity deleteJobInterview(@PathVariable Long id) {
        return jobInterviewService.deleteJobInterview(id);
    }
}
