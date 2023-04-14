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
    public ResponseEntity<List<JobInterviewDto>> getAll(@RequestHeader("Token") String accessToken) {
        return jobInterviewService.getAll(accessToken);
    }

    @GetMapping("/get-JobInterview/{id}")
    public ResponseEntity<JobInterviewDto> findById(@RequestHeader("Token") String accessToken, @PathVariable Long id) {
        return jobInterviewService.findById(accessToken, id);
    }

    @PostMapping("/create-job-interview")
    public ResponseEntity createJobInterview(@RequestHeader("Token") String accessToken, @RequestBody JobInterviewInsertionDto dto) {
        return jobInterviewService.createJobInterview(accessToken, dto);
    }

    @PostMapping("/update-job-interview")
    public ResponseEntity updateJobInterview(@RequestHeader("Token") String accessToken, @RequestBody JobInterviewUpdateDto dto) {
        return jobInterviewService.updateJobInterview(accessToken, dto);
    }

    @GetMapping("/delete-job-interview/{id}")
    public ResponseEntity deleteJobInterview(@RequestHeader("Token") String accessToken, @PathVariable Long id) {
        return jobInterviewService.deleteJobInterview(accessToken, id);
    }

    @GetMapping("/failed-job-interview-percentage")
    public ResponseEntity<String> getFailedJobInterviewPercentage() {
        return jobInterviewService.getFailedJobInterviewPercentage();
    }

    @GetMapping("candidate-avarage-job-interview-time/{id}")
    public ResponseEntity<String> getAvarageTimeJobInterview(@PathVariable Long candidateId) {
        return jobInterviewService.getAvarageTimeJobInterview(candidateId);
    }
}
