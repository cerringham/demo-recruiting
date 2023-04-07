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
    public ResponseEntity<JobPositionDto> findById(@RequestHeader("Token") String accessToken, @PathVariable Long id) {
        return jobPositionService.findById(accessToken, id);
    }

    @PostMapping("/insert-job-position")
    public ResponseEntity<JobPositionWithSkillsDto> insertJobPosition (@RequestHeader("Token") String accessToken, @RequestBody JobPositionWithSkillsDto jobPositionWithSkillsDto) {
        return jobPositionService.insertJobPosition(accessToken, jobPositionWithSkillsDto);
    }

    @PostMapping("/update-job-position")
    public ResponseEntity updateJobPosition(@RequestHeader("Token") String accessToken, @RequestParam Long id, @RequestParam String newStatus) {
        return jobPositionService.updateJobPosition(accessToken, id, newStatus);
    }

    @PostMapping("/delete-job-position")
    public ResponseEntity deleteJobPosition(@RequestHeader("Token") String accessToken, @RequestParam Long id) {
        return jobPositionService.deleteJobPosition(accessToken, id);
    }
}
