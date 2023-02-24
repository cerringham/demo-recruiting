package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@NoArgsConstructor
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
    private List<CandidateSkill> candidateSkillList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id")
    private List<JobInterview> candidateList;
}
