package it.proactivity.recruiting.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeDto {

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

    private String expertiseName;

    private String companyName;

    private String companyRoleName;

    public EmployeeDto(String fiscalCode, String name, String surname, String cityOfBirth, String countryOfBirth,
                       String cityOfResidence, String streetOfResidence, String regionOfResidence,
                       String countryOfResidence, String email, String phoneNumber, String gender, Boolean isActive,
                       String birthDate, String expertiseName, String companyName, String companyRoleName) {
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
        this.expertiseName = expertiseName;
        this.companyName = companyName;
        this.companyRoleName = companyRoleName;
    }

    @Override
    public String toString() {
        return name + " " + surname + " nato a : " + cityOfBirth + "\n"
                + "Residente a : " + cityOfResidence + " all'indirizzo : " + streetOfResidence + " nella regione: " +
                regionOfResidence + " (" + countryOfResidence + ")" + "\n"
                + "email : " + email + " phoneNumber : " + phoneNumber + " gender : " + gender + " data di nascita : " +
                birthDate;
    }
}
