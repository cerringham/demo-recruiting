package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "job_interview")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JobInterview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime hour;

    @Column(name = "place")
    private String place;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private JobInterviewType jobInterviewType;

    @ManyToOne
    private JobPosition jobPosition;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "note")
    private String note;

    @ManyToOne
    private JobInterviewStatus jobInterviewStatus;

    @Column(name = "is_active")
    private Boolean isActive;

    @Override
    public String toString() {
        return "JobInterview{" +
                "id=" + id +
                ", date=" + date +
                ", hour=" + hour +
                ", place='" + place + '\'' +
                ", candidate=" + candidate +
                ", employee=" + employee +
                ", jobInterviewType=" + jobInterviewType +
                ", jobPosition=" + jobPosition +
                ", rating=" + rating +
                ", note='" + note + '\'' +
                ", jobInterviewStatus=" + jobInterviewStatus +
                ", isActive=" + isActive +
                '}';
    }
}
