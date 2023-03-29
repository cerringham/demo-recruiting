package it.proactivity.recruiting.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CandidateDto {

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

    public CandidateDto(String fiscalCode, String name, String surname, String cityOfBirth, String countryOfBirth,
                        String cityOfResidence, String streetOfResidence, String regionOfResidence,
                        String countryOfResidence, String email, String phoneNumber, String gender, Boolean isActive,
                        String birthDate) {
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
    }

    @Override
    public String toString() {
        return name + " " + surname + " born to : " + cityOfBirth + "\n"
                + "Resident to : " + cityOfResidence + " address : " + streetOfResidence + " region : " +
                regionOfResidence + " (" + countryOfResidence + ")" + "\n"
                + "email : " + email + " phoneNumber : " + phoneNumber + " gender : " + gender + " date of birth : " +
                birthDate;
    }
}
