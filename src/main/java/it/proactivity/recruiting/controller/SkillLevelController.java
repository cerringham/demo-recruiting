package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.SkillLevelDto;
import it.proactivity.recruiting.service.SkillLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillLevelController {

    @Autowired
    SkillLevelService skillLevelService;

    @GetMapping("/get-all-SkillLevel")
    public ResponseEntity<List<SkillLevelDto>> getAll(@RequestHeader("Token") String accessToken) {
        return skillLevelService.getAll(accessToken);
    }

    @GetMapping("/get-SkillLevel/{id}")
    public ResponseEntity<SkillLevelDto> findById(@RequestHeader("Token") String accessToken, @PathVariable Long id) {
        return skillLevelService.findById(accessToken, id);
    }


}
