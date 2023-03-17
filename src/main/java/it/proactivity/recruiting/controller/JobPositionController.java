package it.proactivity.recruiting.controller;


import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.model.dto.JobPositionWithSkillsDto;
import it.proactivity.recruiting.model.dto.NewAndUrgentJobPositionDto;
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
    public ResponseEntity<JobPositionWithSkillsDto> insertJobPosition(@RequestBody JobPositionWithSkillsDto jobPositionWithSkillsDto) {
        return jobPositionService.insertJobPosition(jobPositionWithSkillsDto);
    }

    @PostMapping("/update-job-position")
    public ResponseEntity updateJobPosition(@RequestParam Long id, @RequestParam String newStatus) {
        return jobPositionService.updateJobPosition(id, newStatus);
    }

    @PostMapping("/delete-job-position")
    public ResponseEntity deleteJobPosition(@RequestParam Long id) {
        return jobPositionService.deleteJobPosition(id);
    }

    @GetMapping("/show-job-position-active")
    public ResponseEntity<List<NewAndUrgentJobPositionDto>> showJobPositionActive() {
        return jobPositionService.showJobPositionActive();
    }
}
