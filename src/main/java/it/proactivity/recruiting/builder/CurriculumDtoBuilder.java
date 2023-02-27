package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.CurriculumDto;

public class CurriculumDtoBuilder {
    private Long candidateId;

    private String candidateName;

    private String candidateSurname;

    private String skillName;

    private String level;

    private CurriculumDtoBuilder (Long candidateId) {

        this.candidateId = candidateId;
    }
    public static CurriculumDtoBuilder newBuilder(Long candidateId) {

        return new CurriculumDtoBuilder(candidateId);
    }
    public CurriculumDtoBuilder candidateName(String candidateName) {
        this.candidateName = candidateName;
        return this;
    }
    public CurriculumDtoBuilder candidateSurname(String candidateSurname) {
        this.candidateSurname = candidateSurname;
        return this;
    }

    public CurriculumDtoBuilder skillName(String skillName) {
        this.skillName = skillName;
        return this;
    }

    public CurriculumDtoBuilder level(String level) {
        this.level = level;
        return this;
    }

    public CurriculumDto buil() {
        return new CurriculumDto(candidateId, candidateName, candidateSurname, skillName, level);
    }
}
