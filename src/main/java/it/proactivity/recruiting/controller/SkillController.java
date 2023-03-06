package it.proactivity.recruiting.controller;


import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("insert-skill")
    public ResponseEntity insertSkill(@RequestBody SkillDto dto) {
        return skillService.insertSkill(dto);
    }

    @GetMapping("/delete-skill/{id}")
    public ResponseEntity deleteSkill(@PathVariable Long id) {
        return skillService.deleteSkill(id);
    }

    @PostMapping("update-skill")
    public ResponseEntity updateSkill(@RequestBody SkillDto dto) {
        return skillService.updateSkill(dto);
    }
}
