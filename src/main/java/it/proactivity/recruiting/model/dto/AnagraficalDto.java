package it.proactivity.recruiting.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnagraficalDto {

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

    public AnagraficalDto(String name, String surname, String fiscalCode, String cityOfBirth, String countryOfBirth,
                          String cityOfResidence, String streetOfResidence, String regionOfResidence, String countryOfResidence,
                          String email, String phoneNumber, String gender, String birthDate, String expertise) {


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
    }

    public AnagraficalDto(Long id, String name, String surname, String fiscalCode, String cityOfBirth, String countryOfBirth,
                          String cityOfResidence, String streetOfResidence, String regionOfResidence, String countryOfResidence,
                          String email, String phoneNumber, String gender, String birthDate, String expertise) {

        this.id = id;
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
    }
}
