package it.proactivity.recruiting.builder;


import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.CompanyRole;
import it.proactivity.recruiting.model.Employee;
import it.proactivity.recruiting.model.Expertise;

import java.time.LocalDate;

public class EmployeeBuilder {

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

    private Expertise expertise;

    private CompanyRole companyRole;

    private Company company;

    private Boolean isActive;

    private LocalDate birthDate;

    private EmployeeBuilder(String name) {
        this.name = name;
    }

    public static EmployeeBuilder newBuilder(String name) {
        return new EmployeeBuilder(name);
    }


    public EmployeeBuilder fiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
        return this;
    }

    public EmployeeBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public EmployeeBuilder cityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
        return this;
    }

    public EmployeeBuilder countryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
        return this;
    }

    public EmployeeBuilder cityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
        return this;
    }

    public EmployeeBuilder streetOfResidence(String streetOfResidence) {
        this.streetOfResidence = streetOfResidence;
        return this;
    }

    public EmployeeBuilder regionOfResidence(String regionOfResidence) {
        this.regionOfResidence = regionOfResidence;
        return this;
    }

    public EmployeeBuilder countryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
        return this;
    }

    public EmployeeBuilder email(String email) {
        this.email = email;
        return this;
    }

    public EmployeeBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public EmployeeBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public EmployeeBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public EmployeeBuilder birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public EmployeeBuilder expertise(Expertise expertise) {
        this.expertise = expertise;
        return this;
    }

    public EmployeeBuilder company(Company company) {
        this.company = company;
        return this;
    }

    public EmployeeBuilder companyRole(CompanyRole companyRole) {
        this.companyRole = companyRole;
        return this;
    }

    public Employee build() {
        return new Employee(name, surname, fiscalCode, cityOfBirth, countryOfBirth, cityOfResidence, countryOfResidence,
                streetOfResidence, regionOfResidence, email, phoneNumber, gender, birthDate, expertise, company,
                companyRole, isActive);
    }
}
