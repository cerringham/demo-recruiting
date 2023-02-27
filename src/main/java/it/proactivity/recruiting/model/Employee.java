package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fiscalCode='" + fiscalCode + '\'' +
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
                ", expertise=" + expertise +
                ", companyRole=" + companyRole +
                ", company=" + company +
                ", isActive=" + isActive +
                ", birthDate=" + birthDate +
                ", jobInterviewList=" + jobInterviewList +
                '}';
    }
}
