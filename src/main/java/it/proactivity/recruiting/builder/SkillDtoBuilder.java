package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.ExpertiseDto;
import it.proactivity.recruiting.model.dto.SkillDto;

public class SkillDtoBuilder {
    private Long id;

    private String name;

    private Boolean isActive;

    private SkillDtoBuilder(String name) {
        this.name = name;
    }

    public static SkillDtoBuilder newBuilder(String name) {
        return new SkillDtoBuilder(name);
    }

    public SkillDtoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SkillDtoBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public SkillDto build() {
        return new SkillDto(id, name, isActive);
    }
}
