package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "job_position_status")
@Getter
@Setter
@NoArgsConstructor
public class JobPositionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "priority")
    private Integer priority;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_position_status_id")
    private List<JobPosition> jobPositionList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPositionStatus that = (JobPositionStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(isActive, that.isActive) && Objects.equals(jobPositionList, that.jobPositionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isActive, jobPositionList);
    }
}
