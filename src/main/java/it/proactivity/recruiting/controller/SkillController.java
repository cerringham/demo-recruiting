package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SkillController {
    @Autowired
    SkillService skillService;

    @GetMapping("/get-all-skills")
    public ResponseEntity<Set<SkillDto>> getAll() {
        return skillService.getAll();
    }

    @GetMapping("/get-skill")
    public ResponseEntity<SkillDto> findById(@RequestParam Long id) {
        return skillService.findById(id);
    }

    @PostMapping("/delete-skill")
    public ResponseEntity<SkillDto> deleteSkill(@RequestParam Long id) {
        return skillService.deleteSkill(id);
    }

    @PostMapping("/insert-skill")
    public ResponseEntity<SkillDto> insertSkill(@RequestBody SkillDto skillDto) {
        return skillService.insertSkill(skillDto);
    }

    @PostMapping("update-skill")
    public ResponseEntity<SkillDto> updateSkill(@RequestBody SkillDto skillDto) {
        return skillService.updateSkill(skillDto);
    }

}
