package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_position_status_id")
    private List<JobPosition> jobPositionList;

    @Override
    public String toString() {
        return "JobPositionStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", jobPositionList=" + jobPositionList +
                '}';
    }
}
