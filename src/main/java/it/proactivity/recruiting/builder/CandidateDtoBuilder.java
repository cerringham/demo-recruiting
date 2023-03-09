package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.CandidateDto;

public class CandidateDtoBuilder {

    private String fiscalCode;

    private final String name;

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

    private CandidateDtoBuilder(String name) {
        this.name = name;
    }

    public static CandidateDtoBuilder newBuilder(String name) {
        return new CandidateDtoBuilder(name);
    }

    public CandidateDtoBuilder fiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
        return this;
    }

    public CandidateDtoBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public CandidateDtoBuilder cityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
        return this;
    }

    public CandidateDtoBuilder countryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
        return this;
    }

    public CandidateDtoBuilder cityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
        return this;
    }

    public CandidateDtoBuilder streetOfResidence(String streetOfResidence) {
        this.streetOfResidence = streetOfResidence;
        return this;
    }

    public CandidateDtoBuilder regionOfResidence(String regionOfResidence) {
        this.regionOfResidence = regionOfResidence;
        return this;
    }

    public CandidateDtoBuilder countryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
        return this;
    }

    public CandidateDtoBuilder email(String email) {
        this.email = email;
        return this;
    }

    public CandidateDtoBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CandidateDtoBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public CandidateDtoBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public CandidateDtoBuilder birthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public CandidateDto build() {
        return new CandidateDto(fiscalCode, name, surname, cityOfBirth, countryOfBirth, cityOfResidence, streetOfResidence,
                regionOfResidence, countryOfResidence, email, phoneNumber, gender, isActive, birthDate);

    }
}
