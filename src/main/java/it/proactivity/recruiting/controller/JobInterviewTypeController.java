package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.JobInterviewTypeDto;
import it.proactivity.recruiting.service.JobInterviewTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobInterviewTypeController {

    @Autowired
    JobInterviewTypeService jobInterviewTypeService;

    @GetMapping("/get-all-JobInterviewTypes")
    public ResponseEntity<List<JobInterviewTypeDto>> getAll() {
        return jobInterviewTypeService.getAll();
    }

    @GetMapping("/get-JobInterviewType/{id}")
    public ResponseEntity<JobInterviewTypeDto> getById(@PathVariable Long id) {
        return jobInterviewTypeService.getById(id);
    }
}
