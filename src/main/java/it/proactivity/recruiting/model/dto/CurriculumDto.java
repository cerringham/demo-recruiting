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
    private CandidateDto candidateDto;

    private SkillDto skillDto;

    private String level;

    public CurriculumDto(CandidateDto candidateDto, SkillDto skillDto, String level) {
        this.candidateDto = candidateDto;
        this.skillDto = skillDto;
        this.level = level;
    }

    public CurriculumDto(SkillDto skillDto, String level) {
        this.skillDto = skillDto;
        this.level = level;
    }

    @Override
    public String toString() {
        return candidateDto + " " +
                " " + level;
    }
}
