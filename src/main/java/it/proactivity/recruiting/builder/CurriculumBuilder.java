package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.myEnum.Level;

public class CurriculumBuilder {
    private Candidate candidate;

    private Skill skill;

    private Level level;

    private Boolean isActive;

    private CurriculumBuilder(Candidate candidate) {
        this.candidate = candidate;
    }

    public static CurriculumBuilder newBuilder(Candidate candidate) {
        return new CurriculumBuilder(candidate);
    }

    public CurriculumBuilder skill(Skill skill) {
        this.skill = skill;
        return this;
    }

    public CurriculumBuilder level(Level level) {
        this.level = level;
        return this;
    }

    public CurriculumBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Curriculum build() {
        return new Curriculum(candidate, skill, level, isActive);
    }
}
