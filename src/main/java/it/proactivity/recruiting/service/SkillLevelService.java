package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.SkillLevelDtoBuilder;
import it.proactivity.recruiting.model.SkillLevel;
import it.proactivity.recruiting.model.dto.SkillLevelDto;
import it.proactivity.recruiting.repository.SkillLevelRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.apache.commons.lang3.StringUtils;
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

    public ResponseEntity<List<SkillLevelDto>> getAll() {
        List<SkillLevel> skillLevelList = skillLevelRepository.findAll();
        List<SkillLevelDto> dtoList = skillLevelList.stream()
                .map(s -> createSkillLevelDto(s.getIsActive(), s.getLevel().toString(), s.getSkill().getName(),
                        s.getJobPosition().getTitle())).toList();
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<SkillLevelDto> getById(Long id) {
        globalValidator.validateId(id);
        Optional<SkillLevel> skillLevel = skillLevelRepository.findById(id);
        if (skillLevel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(createSkillLevelDto(skillLevel.get().getIsActive(), skillLevel.get().getLevel().toString(),
                skillLevel.get().getSkill().getName(), skillLevel.get().getJobPosition().getTitle()));
    }

    private SkillLevelDto createSkillLevelDto(Boolean isActive, String level, String skillName, String jobPositionTitle) {
        if (StringUtils.isEmpty(level) || StringUtils.isEmpty(skillName) || StringUtils.isEmpty(jobPositionTitle) ||
        isActive == null){
            throw new IllegalArgumentException("The parameters for the creation of SkillLevelDto can't be null or empty");
        }

        return SkillLevelDtoBuilder.newBuilder(isActive)
                .skillName(skillName)
                .level(level)
                .jobPositionTitle(jobPositionTitle)
                .build();
    }
}
