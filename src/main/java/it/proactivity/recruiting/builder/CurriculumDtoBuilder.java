package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.model.dto.SkillDto;

public class CurriculumDtoBuilder {

    private Long id;
    private CandidateDto candidateId;

    private SkillDto skillId;

    private String level;

    private CurriculumDtoBuilder (CandidateDto candidateId) {

        this.candidateId = candidateId;
    }
    public static CurriculumDtoBuilder newBuilder(CandidateDto candidateId) {

        return new CurriculumDtoBuilder(candidateId);
    }
    public CurriculumDtoBuilder skillId(SkillDto skillId) {
        this.skillId = skillId;
        return this;
    }

    public CurriculumDtoBuilder level(String level) {
        this.level = level;
        return this;
    }

    public CurriculumDto build() {
        return new CurriculumDto(candidateId, skillId, level);
    }
}
