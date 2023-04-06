package it.proactivity.recruiting.controller;


import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.model.dto.JobPositionWithSkillsDto;
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
    public ResponseEntity<List<JobPositionDto>> getAll(@RequestHeader("Token") String accessToken) {
        return jobPositionService.getAll(accessToken);
    }

    @GetMapping("/get-JobPosition/{id}")
    public ResponseEntity<JobPositionDto> findById(@PathVariable Long id, @RequestHeader("Token") String accessToken) {
        return jobPositionService.findById(id, accessToken);
    }

    @PostMapping("/insert-job-position")
    public ResponseEntity<JobPositionWithSkillsDto> insertJobPosition(@RequestBody JobPositionWithSkillsDto jobPositionWithSkillsDto,
                                                                      @RequestHeader("Token") String accessToken) {
        return jobPositionService.insertJobPosition(jobPositionWithSkillsDto, accessToken);
    }

    @PostMapping("/update-job-position")
    public ResponseEntity updateJobPosition(@RequestParam Long id, @RequestParam String newStatus, @RequestHeader("Token") String accessToken) {
        return jobPositionService.updateJobPosition(id, newStatus, accessToken);
    }

    @PostMapping("/delete-job-position")
    public ResponseEntity deleteJobPosition(@RequestParam Long id, @RequestHeader("Token") String accessToken) {
        return jobPositionService.deleteJobPosition(id, accessToken);
    }
}
