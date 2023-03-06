package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    private Expertise expertise;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id")
    private Set<Curriculum> candidateSkillList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id")
    private List<JobInterview> candidateList;

    public Candidate(String fiscalCode, String name, String surname, String cityOfBirth, String countryOfBirth,
                     String cityOfResidence, String streetOfResidence, String regionOfResidence, String countryOfResidence,
                     String email, String phoneNumber, String gender, Boolean isActive, LocalDate birthDate,
                     Expertise expertise) {
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
        this.expertise = expertise;
    }
}
