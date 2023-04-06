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
    public ResponseEntity<List<SkillDto>> getAll(@RequestHeader("Token") String accessToken) {
        return skillService.getAll(accessToken);
    }

    @GetMapping("/get-Skill/{id}")
    public ResponseEntity<SkillDto> findById(@PathVariable Long id, @RequestHeader("Token") String accessToken) {
        return skillService.findById(id, accessToken);
    }


    @PostMapping("insert-skill")
    public ResponseEntity insertSkill(@RequestBody SkillDto dto, @RequestHeader("Token") String accessToken) {
        return skillService.insertSkill(dto, accessToken);
    }

    @GetMapping("/delete-skill/{id}")
    public ResponseEntity deleteSkill(@PathVariable Long id, @RequestHeader("Token") String accessToken) {
        return skillService.deleteSkill(id, accessToken);
    }

    @PostMapping("update-skill")
    public ResponseEntity updateSkill(@RequestBody SkillDto dto, @RequestHeader("Token") String accessToken) {
        return skillService.updateSkill(dto, accessToken);
    }
}
