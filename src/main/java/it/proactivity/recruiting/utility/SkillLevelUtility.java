package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.SkillBuilder;
import it.proactivity.recruiting.builder.SkillLevelBuilder;
import it.proactivity.recruiting.builder.SkillLevelDtoBuilder;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.SkillLevel;
import it.proactivity.recruiting.model.dto.SkillLevelDto;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.SkillRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SkillLevelUtility {

    @Autowired
    SkillRepository skillRepository;

    public SkillLevelDto createSkillLevelDto(Boolean isActive, String level, String skillName, String jobPositionTitle) {
        if (StringUtils.isEmpty(level) || StringUtils.isEmpty(skillName) || StringUtils.isEmpty(jobPositionTitle) ||
                isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of SkillLevelDto can't be null or empty");
        }

        return SkillLevelDtoBuilder.newBuilder(isActive)
                .skillName(skillName)
                .level(level)
                .jobPositionTitle(jobPositionTitle)
                .build();
    }

    public SkillLevel createSkillLevelFromDto(SkillLevelDto skillLevelDto) {
        Optional<Skill> skill = skillRepository.findByNameIgnoreCase(skillLevelDto.getSkillName());
        if (skill.isEmpty()) {
            SkillBuilder.newBuilder(skillLevelDto.getSkillName())
                    .isActive(true)
                    .build();
            skillRepository.save(skill.get());
        }
        return SkillLevelBuilder.newBuilder(skillLevelDto.getIsActive())
                    .skill(skill.get())
                    .level(Level.valueOf(skillLevelDto.getLevel()))
                    .build();
        }
}
