package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.SkillLevel;
import it.proactivity.recruiting.model.dto.SkillLevelDto;
import it.proactivity.recruiting.repository.SkillLevelRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.SkillLevelUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillLevelService {

    @Autowired
    SkillLevelRepository skillLevelRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    SkillLevelUtility skillLevelUtility;

    public ResponseEntity<List<SkillLevelDto>> getAll(String accessToken) {

        if (!skillLevelUtility.authorizeSkillLevelService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<SkillLevel> skillLevelList = skillLevelRepository.findByIsActive(true);
        List<SkillLevelDto> dtoList = skillLevelList.stream()
                .map(s -> skillLevelUtility.createSkillLevelDto(s.getIsActive(), s.getLevel().toString(), s.getSkill().getName(),
                        s.getJobPosition().getTitle())).toList();
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<SkillLevelDto> findById(String accessToken, Long id) {

        if (!skillLevelUtility.authorizeSkillLevelService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        globalValidator.validateId(id);
        Optional<SkillLevel> skillLevel = skillLevelRepository.findByIdAndIsActive(id, true);
        if (skillLevel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(skillLevelUtility.createSkillLevelDto(skillLevel.get().getIsActive(), skillLevel.get().getLevel().toString(),
                skillLevel.get().getSkill().getName(), skillLevel.get().getJobPosition().getTitle()));
    }
}
