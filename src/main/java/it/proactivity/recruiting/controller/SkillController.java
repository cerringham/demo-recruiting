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
    public ResponseEntity<SkillDto> findById(@RequestHeader("Token") String accessToken, @PathVariable Long id) {
        return skillService.findById(accessToken, id);
    }


    @PostMapping("insert-skill")
    public ResponseEntity insertSkill(@RequestHeader("Token") String accessToken, @RequestBody SkillDto dto) {
        return skillService.insertSkill(accessToken, dto);
    }

    @GetMapping("/delete-skill/{id}")
    public ResponseEntity deleteSkill(@RequestHeader("Token") String accessToken, @PathVariable Long id) {
        return skillService.deleteSkill(accessToken, id);
    }

    @PostMapping("update-skill")
    public ResponseEntity updateSkill(@RequestHeader("Token") String accessToken, @RequestBody SkillDto dto) {
        return skillService.updateSkill(accessToken, dto);
    }
}
