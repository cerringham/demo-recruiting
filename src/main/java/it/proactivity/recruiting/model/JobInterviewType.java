package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "job_interview_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JobInterviewType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_interview_type_id")
    private List<JobInterview> jobInterviewList;

    @Override
    public String toString() {
        return "JobInterviewType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobInterviewList=" + jobInterviewList +
                '}';
    }
}
