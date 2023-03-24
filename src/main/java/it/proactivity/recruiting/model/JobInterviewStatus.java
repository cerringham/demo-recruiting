package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "job_interview_status")
@Getter
@Setter
@NoArgsConstructor
public class JobInterviewStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "sequence_order")
    private Integer sequenceOrder;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_interview_status_id")
    private List<JobInterview> jobInterviewList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInterviewStatus that = (JobInterviewStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description,
                that.description) && Objects.equals(isActive, that.isActive) && Objects.equals(sequenceOrder,
                that.sequenceOrder) && Objects.equals(jobInterviewList, that.jobInterviewList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isActive, sequenceOrder, jobInterviewList);
    }
}
