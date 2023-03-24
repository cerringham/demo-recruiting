package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

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

    public JobInterview(Long id, LocalDate date, LocalTime hour, String place,
                        Boolean isActive, Candidate candidate, Employee employee, JobPosition jobPosition,
                        JobInterviewStatus jobInterviewStatus) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.place = place;
        this.isActive = isActive;
        this.candidate = candidate;
        this.employee = employee;
        this.jobPosition = jobPosition;
        this.jobInterviewStatus = jobInterviewStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInterview that = (JobInterview) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(hour, that.hour) &&
                Objects.equals(place, that.place) && Objects.equals(rating, that.rating) &&
                Objects.equals(note, that.note) && Objects.equals(isActive, that.isActive) &&
                Objects.equals(candidate, that.candidate) && Objects.equals(employee, that.employee) &&
                Objects.equals(jobPosition, that.jobPosition) &&
                Objects.equals(jobInterviewStatus, that.jobInterviewStatus) &&
                Objects.equals(jobInterviewType, that.jobInterviewType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, hour, place, rating, note, isActive, candidate, employee, jobPosition,
                jobInterviewStatus, jobInterviewType);
    }
}
