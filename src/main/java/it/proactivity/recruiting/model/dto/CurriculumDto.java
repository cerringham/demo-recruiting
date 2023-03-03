package it.proactivity.recruiting.model.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CurriculumDto {

    private Long id;
    private CandidateDto candidateId;

    private SkillDto skillId;

    private String level;

    public CurriculumDto(CandidateDto candidateId, SkillDto skillId, String level) {
        this.candidateId = candidateId;
        this.skillId = skillId;
        this.level = level;
    }

    @Override
    public String toString() {
        return candidateId + " " +
                " " + level;
    }
}
