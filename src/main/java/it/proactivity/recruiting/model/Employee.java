package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fiscal_code")
    private String fiscalCode;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "city_of_birth")
    private String cityOfBirth;

    @Column(name = "country_of_birth")
    private String countryOfBirth;

    @Column(name = "city_of_residence")
    private String cityOfResidence;

    @Column(name = "street_of_residence")
    private String streetOfResidence;

    @Column(name = "region_of_residence")
    private String regionOfResidence;

    @Column(name = "country_of_residence")
    private String countryOfResidence;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @ManyToOne
    private Expertise expertise;

    @ManyToOne
    private CompanyRole companyRole;

    @ManyToOne
    private Company company;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private List<JobInterview> jobInterviewList;

    public Employee(String name, String surname, String fiscalCode, String cityOfBirth, String countryOfBirth,
                    String cityOfResidence, String countryOfResidence, String streetOfResidence, String regionOfResidence,
                    String email, String phoneNumber, String gender, LocalDate birthDate, Expertise expertise,
                    Company company, CompanyRole companyRole, Boolean isActive) {

        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.cityOfBirth = cityOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.cityOfResidence = cityOfResidence;
        this.countryOfResidence = countryOfResidence;
        this.streetOfResidence = streetOfResidence;
        this.regionOfResidence = regionOfResidence;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.expertise = expertise;
        this.company = company;
        this.companyRole = companyRole;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(fiscalCode, employee.fiscalCode) &&
                Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) &&
                Objects.equals(cityOfBirth, employee.cityOfBirth) &&
                Objects.equals(countryOfBirth, employee.countryOfBirth) &&
                Objects.equals(cityOfResidence, employee.cityOfResidence) &&
                Objects.equals(streetOfResidence, employee.streetOfResidence) &&
                Objects.equals(regionOfResidence, employee.regionOfResidence) &&
                Objects.equals(countryOfResidence, employee.countryOfResidence) &&
                Objects.equals(email, employee.email) && Objects.equals(phoneNumber, employee.phoneNumber) &&
                Objects.equals(gender, employee.gender) && Objects.equals(expertise, employee.expertise) &&
                Objects.equals(companyRole, employee.companyRole) && Objects.equals(company, employee.company) &&
                Objects.equals(isActive, employee.isActive) && Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(jobInterviewList, employee.jobInterviewList);
    }

}
