package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.SkillBuilder;
import it.proactivity.recruiting.builder.SkillDtoBuilder;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Component;

@Component
public class SkillUtility {

    public SkillDto createSkillDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of skillDto can't be null or empty");
        }

        return SkillDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }

    public Skill createSkill(SkillDto dto) {
        return SkillBuilder.newBuilder(WordUtils.capitalizeFully(dto.getName()))
                .isActive(dto.getIsActive())
                .build();
    }
}
