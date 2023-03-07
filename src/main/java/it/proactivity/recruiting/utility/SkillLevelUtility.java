package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.SkillLevelDtoBuilder;
import it.proactivity.recruiting.model.dto.SkillLevelDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class SkillLevelUtility {

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
}
