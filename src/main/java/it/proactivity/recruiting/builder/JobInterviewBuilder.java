package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.*;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import org.springframework.cglib.core.Local;

import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;

public class JobInterviewBuilder {

    private Long id;

    private LocalDate date;

    private LocalTime hour;

    private Employee employeeId;

    private Candidate candidateId;

    private String place;

    private JobPosition jobPositionId;

    private JobInterviewStatus status;

    private Boolean isActive;

    private JobInterviewBuilder(LocalDate date) {
        this.date = date;
    }

    public static JobInterviewBuilder newBuilder(LocalDate date ) {
        return new JobInterviewBuilder(date);
    }

    public JobInterviewBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public JobInterviewBuilder hour(LocalTime hour) {
        this.hour = hour;
        return this;
    }

    public JobInterviewBuilder employeeId(Employee employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public JobInterviewBuilder candidateId(Candidate candidateId) {
        this.candidateId = candidateId;
        return this;
    }

    public JobInterviewBuilder place(String place) {
        this.place = place;
        return this;
    }

    public JobInterviewBuilder jobPositionId(JobPosition jobPositionId) {
        this.jobPositionId = jobPositionId;
        return this;
    }

    public JobInterviewBuilder jobInterviewStatus(JobInterviewStatus status) {
        this.status = status;
        return this;
    }

    public JobInterviewBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public JobInterview build() {
        return new JobInterview(id, date, hour, place,  isActive, candidateId, employeeId,  jobPositionId, status);
    }
}
