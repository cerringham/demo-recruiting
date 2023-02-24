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

    private Long candidateId;

    private String candidateName;

    private String candidateSurname;

    private String skillName;

    private String level;

    public CurriculumDto(Long candidateId, String candidateName, String candidateSurname, String skillName, String level) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.candidateSurname = candidateSurname;
        this.skillName = skillName;
        this.level = level.toString();
    }

    @Override
    public String toString() {
        return candidateId + " " + candidateName + " " + candidateSurname + " " + skillName +
                " " + level;
    }
}
