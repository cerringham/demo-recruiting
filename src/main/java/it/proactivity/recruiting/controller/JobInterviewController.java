package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.JobInterviewDto;
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


    @PostMapping("/update-job-interview")
    public ResponseEntity updateJobInterview(@RequestBody JobInterviewDto jobInterviewDto) {
        return jobInterviewService.updateJobInterview(jobInterviewDto);
    }

    @PostMapping("/delete-job-interview")
    public ResponseEntity deleteJobInterview (@RequestParam Long id) {
        return jobInterviewService.deleteJobInterview(id);
    }
}
