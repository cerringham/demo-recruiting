package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.SkillLevel;
import it.proactivity.recruiting.myEnum.Level;


public class SkillLevelBuilder {

    private Boolean isActive;

    private Level level;

    private Skill skill;

    private JobPosition jobPosition;

    private SkillLevelBuilder(Skill skill) {
        this.skill = skill;
    }

    public static SkillLevelBuilder newBuilder(Skill skill) {
        return new SkillLevelBuilder(skill);
    }

    public SkillLevelBuilder level(Level level) {
        this.level = level;
        return this;
    }

    public SkillLevelBuilder jobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
        return this;
    }

    public SkillLevelBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public SkillLevel build() {
        return new SkillLevel(skill, level, jobPosition, isActive);
    }
}
