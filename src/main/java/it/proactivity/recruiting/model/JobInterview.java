package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "job_interview")
@Getter
@Setter
@NoArgsConstructor
public class JobInterview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "hour")
    private LocalTime hour;

    @Column(name = "place")
    private String place;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "note")
    private String note;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private JobPosition jobPosition;

    @ManyToOne
    private JobInterviewStatus jobInterviewStatus;

    @ManyToOne
    private JobInterviewType jobInterviewType;

    public JobInterview(Candidate candidate, Employee employee, JobPosition jobPosition,
                        JobInterviewStatus jobInterviewStatus, LocalTime hour, LocalDate date, String place,
                        JobInterviewType jobInterviewType, Boolean isActive) {

        this.date = date;
        this.hour = hour;
        this.jobInterviewStatus = jobInterviewStatus;
        this.jobPosition = jobPosition;
        this.employee = employee;
        this.candidate = candidate;
        this.place = place;
        this.jobInterviewType = jobInterviewType;
        this.isActive = isActive;
    }
}
