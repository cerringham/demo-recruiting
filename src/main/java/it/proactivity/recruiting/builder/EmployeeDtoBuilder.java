package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.EmployeeDto;

public class EmployeeDtoBuilder {
    private String fiscalCode;

    private String name;

    private String surname;

    private String birthDate;

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

    private EmployeeDtoBuilder (String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public static EmployeeDtoBuilder newBuilder(String fiscalCode) {

        return new EmployeeDtoBuilder(fiscalCode);
    }

    public EmployeeDtoBuilder name (String name) {
        this.name = name;
        return this;
    }

    public EmployeeDtoBuilder surname (String surname) {
        this.surname = surname;
        return this;
    }

    public EmployeeDtoBuilder birthDate (String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public EmployeeDtoBuilder cityOfBirth (String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
        return this;
    }

    public EmployeeDtoBuilder countryOfBirth (String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
        return this;
    }

    public EmployeeDtoBuilder cityOfResidence (String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
        return this;
    }

    public EmployeeDtoBuilder streetOfResidence (String streetOfResidence) {
        this.streetOfResidence = streetOfResidence;
        return this;
    }

    public EmployeeDtoBuilder regionOfResidence (String regionOfResidence) {
        this.regionOfResidence = regionOfResidence;
        return this;
    }

    public EmployeeDtoBuilder countryOfResidence (String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
        return this;
    }

    public EmployeeDtoBuilder email (String email) {
        this.email = email;
        return this;
    }

    public EmployeeDtoBuilder phoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public EmployeeDtoBuilder gender (String gender) {
        this.gender = gender;
        return this;
    }

    public EmployeeDtoBuilder isActive (Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public EmployeeDto build() {
        return new EmployeeDto(fiscalCode, name, surname, cityOfBirth, countryOfBirth, cityOfResidence, streetOfResidence,
                regionOfResidence, countryOfResidence,email , phoneNumber, gender, isActive, birthDate);
    }
}
