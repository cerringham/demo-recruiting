package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.Skill;

public class SkillBuilder {

    private String name;

    private Boolean isActive;

    private SkillBuilder(String name) {
        this.name = name;
    }

    public static SkillBuilder newBuilder(String name) {
        return new SkillBuilder(name);
    }

    public SkillBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Skill build() {
        return new Skill(name, isActive);
    }
}
