package it.proactivity.recruiting.model.dto;

import it.proactivity.recruiting.myEnum.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
public class CandidateInformationDto {

    private Long id;
    private String name;

    private String surname;

    private String fiscalCode;

    private String cityOfBirth;

    private String countryOfBirth;

    private String cityOfResidence;

    private String streetOfResidence;

    private String regionOfResidence;

    private String countryOfResidence;

    private String email;

    private String phoneNumber;

    private String gender;

    private String birthDate;

    private String expertise;

    private Map<String, Level> skillLevelMap;

    public CandidateInformationDto(String name, String surname, String fiscalCode, String cityOfBirth,
                                   String countryOfBirth, String cityOfResidence, String streetOfResidence,
                                   String regionOfResidence, String countryOfResidence, String email, String phoneNumber,
                                   String gender, String birthDate, String expertise, Map<String, Level> skillLevelMap) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.cityOfBirth = cityOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.cityOfResidence = cityOfResidence;
        this.streetOfResidence = streetOfResidence;
        this.regionOfResidence = regionOfResidence;
        this.countryOfResidence = countryOfResidence;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.expertise = expertise;
        this.skillLevelMap = skillLevelMap;
    }
}
