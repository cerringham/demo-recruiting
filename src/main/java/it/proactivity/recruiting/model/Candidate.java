package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "candidate")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Candidate {

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id")
    private List<Curriculum> curriculumList;

    @Column(name = "is_active")
    private Boolean isActive;

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", fiscalCode='" + fiscalCode + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", cityOfBirth='" + cityOfBirth + '\'' +
                ", cityOfResidence='" + cityOfResidence + '\'' +
                ", streetOfResidence='" + streetOfResidence + '\'' +
                ", regionOfResidence='" + regionOfResidence + '\'' +
                ", countryOfResidence='" + countryOfResidence + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", expertise=" + expertise +
                ", curriculumList=" + curriculumList +
                ", isActive=" + isActive +
                '}';
    }
}
