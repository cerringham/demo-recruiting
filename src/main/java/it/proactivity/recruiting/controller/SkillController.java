package it.proactivity.recruiting.controller;


import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping("/get-all-Skill")
    public ResponseEntity<List<SkillDto>> getAll() {
        return skillService.getAll();
    }

    @GetMapping("/get-Skill/{id}")
    public ResponseEntity<SkillDto> findById(@PathVariable Long id) {
        return skillService.findById(id);
    }


}
