package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @Column(name = "birth_date")
    private LocalDate birthDate;

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
    private Company company;

    @ManyToOne
    private CompanyRole companyRole;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private List<JobInterview> jobInterviewList;

    @Column(name = "is_active")
    private Boolean isActive;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fiscalCode='" + fiscalCode + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
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
                ", company=" + company +
                ", companyRole=" + companyRole +
                ", jobInterviewList=" + jobInterviewList +
                ", isActive=" + isActive +
                '}';
    }
}
