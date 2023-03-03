package it.proactivity.recruiting.model.dto;

import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Expertise;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CandidateWithSkillDto {

    private Long id;

    private String fiscalCode;

    private String name;

    private String surname;

    private String cityOfBirth;

    private String countryOfBirth;

    private String cityOfResidence;

    private String streetOfResidence;

    private String regionOfResidence;

    private String countryOfResidence;

    private String email;

    private String phoneNumber;

    private String gender;

    private Boolean isActive;

    private String birthDate;

    private ExpertiseDto expertise;

    private Set<CurriculumDto> curriculumList;

    @Override
    public String toString() {
        return "CandidateWithSkillDto{" +
                "fiscalCode='" + fiscalCode + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cityOfBirth='" + cityOfBirth + '\'' +
                ", countryOfBirth='" + countryOfBirth + '\'' +
                ", cityOfResidence='" + cityOfResidence + '\'' +
                ", streetOfResidence='" + streetOfResidence + '\'' +
                ", regionOfResidence='" + regionOfResidence + '\'' +
                ", countryOfResidence='" + countryOfResidence + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", isActive=" + isActive +
                ", birthDate='" + birthDate + '\'' +
                ", expertise=" + expertise +
                ", curriculumList=" + curriculumList +
                '}';
    }
}
