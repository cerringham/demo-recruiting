package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class JobInterviewBuilder {

    private final Candidate candidate;

    private LocalTime hour;

    private LocalDate date;

    private String place;

    private JobInterviewStatus jobInterviewStatus;

    private Employee employee;

    private JobPosition jobPosition;

    private JobInterviewType jobInterviewType;

    private Boolean isActive;

    private JobInterviewBuilder(Candidate candidate) {
        this.candidate = candidate;
    }

    public static JobInterviewBuilder newBuilder(Candidate candidate) {
        return new JobInterviewBuilder(candidate);
    }

    public JobInterviewBuilder hour(LocalTime hour) {
        this.hour = hour;
        return this;
    }

    public JobInterviewBuilder date(LocalDate date) {
        this.date = date;
        return this;
    }

    public JobInterviewBuilder place(String place) {
        this.place = place;
        return this;
    }

    public JobInterviewBuilder jobInterviewStatus(JobInterviewStatus jobInterviewStatus) {
        this.jobInterviewStatus = jobInterviewStatus;
        return this;
    }

    public JobInterviewBuilder employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public JobInterviewBuilder jobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
        return this;
    }

    public JobInterviewBuilder jobInterviewType(JobInterviewType jobInterviewType) {
        this.jobInterviewType = jobInterviewType;
        return this;
    }

    public JobInterviewBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public JobInterview build() {
        return new JobInterview(candidate, employee, jobPosition, jobInterviewStatus, hour, date, place, jobInterviewType,
                isActive);
    }
}
