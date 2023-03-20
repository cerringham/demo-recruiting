package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.SkillLevel;
import it.proactivity.recruiting.myEnum.Level;

public class SkillLevelBuilder {

    private final Boolean isActive;

    private Level level;

    private Skill skill;

    private SkillLevelBuilder(Boolean isActive) {
        this.isActive = isActive;
    }

    public static SkillLevelBuilder newBuilder(Boolean isActive) {
        return new SkillLevelBuilder(isActive);
    }

    public SkillLevelBuilder level(Level level) {
        this.level = level;
        return this;
    }

    public SkillLevelBuilder skill(Skill skill) {
        this.skill = skill;
        return this;
    }

    public SkillLevel build() {
        return new SkillLevel(isActive, level, skill);
    }

}
