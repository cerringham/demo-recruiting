package it.proactivity.recruiting.model.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CandidateSkillDto {

    private Long candidateId;

    private String candidateName;

    private String candidateSurname;

    private String skillName;

    public CandidateSkillDto(Long candidateId, String candidateName, String candidateSurname, String skillName) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.candidateSurname = candidateSurname;
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return candidateId + " " + candidateName + " " + candidateSurname + " " + skillName;
    }
}
