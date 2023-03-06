package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.SkillBuilder;
import it.proactivity.recruiting.builder.SkillDtoBuilder;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SkillUtility {

    public SkillDto createSkillDto(Skill skill) {
        if (skill == null) {
            throw new IllegalArgumentException();
        }
        return SkillDtoBuilder.newBuilder(skill.getName())
                .isActive(skill.getIsActive())
                .build();
    }

    public Skill createSkillFromDto(SkillDto skillDto) {
        if (skillDto == null) {
            throw new IllegalArgumentException();
        }
        return SkillBuilder.newBuilder(skillDto.getName())
                .isActive(skillDto.getIsActive())
                .build();
    }

    public Set<Skill> createSkillSetFromDto(Set<SkillDto> skillDtoSet) {
        if (skillDtoSet.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Set<Skill> skills = skillDtoSet.stream()
                .map(s -> SkillBuilder.newBuilder(s.getName())
                        .isActive(true)
                        .build())
                .collect(Collectors.toSet());
        return skills;
    }
}
