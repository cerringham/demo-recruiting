package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "job_interview_type")
@Getter
@Setter
@NoArgsConstructor
public class JobInterviewType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_interview_type_id")
    private List<JobInterview> jobInterviewList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInterviewType that = (JobInterviewType) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(isActive, that.isActive) && Objects.equals(jobInterviewList, that.jobInterviewList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isActive, jobInterviewList);
    }
}
