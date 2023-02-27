package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class CurriculumController {

    @Autowired
    CurriculumService curriculumService;

    @GetMapping("/get-all-candidateSkills")
    public ResponseEntity<Set<CurriculumDto>> getAll() {
        return curriculumService.getAll();
    }

    @GetMapping("/get-candidateSkill/{id}")
    public ResponseEntity<CurriculumDto> findById(@PathVariable Long id) {
        return curriculumService.findById(id);
    }
}
