package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "job_position")
@Getter
@Setter
@NoArgsConstructor
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "area")
    private String area;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    private Company company;

    @ManyToOne
    private JobPositionStatus jobPositionStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_position_id")
    private List<JobInterview> jobInterviewList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_position_id")
    private List<SkillLevel> skillList;
}
