package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

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

    public JobPosition(String title, String area, String description, String city, String region, String country,
                       Company company, JobPositionStatus jobPositionStatus, Boolean isActive) {
        this.title = title;
        this.area = area;
        this.description = description;
        this.city = city;
        this.region = region;
        this.country = country;
        this.company = company;
        this.jobPositionStatus = jobPositionStatus;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPosition that = (JobPosition) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) &&
                Objects.equals(area, that.area) && Objects.equals(description, that.description) &&
                Objects.equals(city, that.city) && Objects.equals(region, that.region) &&
                Objects.equals(country, that.country) && Objects.equals(isActive, that.isActive) &&
                Objects.equals(company, that.company) && Objects.equals(jobPositionStatus, that.jobPositionStatus) &&
                Objects.equals(jobInterviewList, that.jobInterviewList) && Objects.equals(skillList, that.skillList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, area, description, city, region, country, isActive, company, jobPositionStatus,
                jobInterviewList, skillList);
    }
}
