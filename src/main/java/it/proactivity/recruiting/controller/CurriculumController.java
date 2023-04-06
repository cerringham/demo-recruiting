package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurriculumController {

    @Autowired
    CurriculumService curriculumService;

    @GetMapping("/get-all-candidateSkills")
    public ResponseEntity<List<CurriculumDto>> getAll(@RequestHeader("Token") String accessToken) {
        return curriculumService.getAll(accessToken);
    }

    @GetMapping("/get-candidateSkill/{id}")
    public ResponseEntity<CurriculumDto> findById(@PathVariable Long id, @RequestHeader("Token") String accessToken) {
        return curriculumService.findById(id, accessToken);
    }
}
