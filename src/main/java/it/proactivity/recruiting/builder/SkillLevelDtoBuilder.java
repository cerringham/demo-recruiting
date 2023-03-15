package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.SkillLevelDto;

public class SkillLevelDtoBuilder {

    private Boolean isActive;

    private String level;

    private String skillName;

    private String jobPositionTitle;

    private SkillLevelDtoBuilder(Boolean isActive) {
        this.isActive = isActive;
    }

    public static SkillLevelDtoBuilder newBuilder(Boolean isActive) {
        return new SkillLevelDtoBuilder(isActive);
    }

    public SkillLevelDtoBuilder level(String level) {
        this.level = level;
        return this;
    }

    public SkillLevelDtoBuilder skillName(String skillName) {
        this.skillName = skillName;
        return this;
    }

    public SkillLevelDtoBuilder jobPositionTitle(String jobPositionTitle) {
        this.jobPositionTitle = jobPositionTitle;
        return this;
    }

    public SkillLevelDto build() {
        return new SkillLevelDto(level, skillName);
    }
}
