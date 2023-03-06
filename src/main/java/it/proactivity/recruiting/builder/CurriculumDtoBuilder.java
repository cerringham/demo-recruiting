package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.model.dto.SkillDto;

public class CurriculumDtoBuilder {

    private Long id;
    private CandidateDto candidateDto;

    private SkillDto skillDto;

    private String level;

    private CurriculumDtoBuilder (CandidateDto candidateDto) {

        this.candidateDto = candidateDto;
    }
    public static CurriculumDtoBuilder newBuilder(CandidateDto candidateDto) {

        return new CurriculumDtoBuilder(candidateDto);
    }
    public CurriculumDtoBuilder skillDto(SkillDto skillDto) {
        this.skillDto = skillDto;
        return this;
    }

    public CurriculumDtoBuilder level(String level) {
        this.level = level;
        return this;
    }

    public CurriculumDto build() {
        return new CurriculumDto(candidateDto, skillDto, level);
    }
}
