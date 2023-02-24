package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "job_position")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @ManyToOne
    private Company company;

    @ManyToOne
    private JobPositionStatus jobPositionStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_position_id")
    private List<JobInterview> jobInterviewList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_position_id")
    private List<Requirement> requirementList;

    @Column(name = "is_active")
    private Boolean isActive;

    @Override
    public String toString() {
        return "JobPosition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", area='" + area + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", company=" + company +
                ", jobPositionStatus=" + jobPositionStatus +
                ", jobInterviewList=" + jobInterviewList +
                ", requirementList=" + requirementList +
                ", isActive=" + isActive +
                '}';
    }
}
