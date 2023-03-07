package it.proactivity.recruiting.model.dto;

import lombok.*;

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

    private String expertise;

    private Set<SkillDto> skillDtoSet;

    public CandidateWithSkillDto(String fiscalCode, String name, String surname, String cityOfBirth, String countryOfBirth,
                                 String cityOfResidence, String streetOfResidence, String regionOfResidence,
                                 String countryOfResidence, String email, String phoneNumber, String gender,
                                 Boolean isActive, String birthDate, String expertise, Set<SkillDto> skillDtoSet) {
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.cityOfBirth = cityOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.cityOfResidence = cityOfResidence;
        this.streetOfResidence = streetOfResidence;
        this.regionOfResidence = regionOfResidence;
        this.countryOfResidence = countryOfResidence;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.isActive = isActive;
        this.birthDate = birthDate;
        this.expertise = expertise;
        this.skillDtoSet = skillDtoSet;
    }

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
                '}';
    }
}
