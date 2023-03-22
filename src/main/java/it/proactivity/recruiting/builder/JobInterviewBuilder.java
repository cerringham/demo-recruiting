package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.JobInterviewDto;

import java.io.StringReader;

public class JobInterviewBuilder {

    private Long id;

    private String date;

    private String hour;

    private Long employeeId;

    private Integer rating;

    private String note;

    private Boolean isActive;

    private JobInterviewBuilder(String date) {
        this.date = date;
    }

    public static JobInterviewBuilder newBuilder(String date ) {
        return new JobInterviewBuilder(date);
    }

    public JobInterviewBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public JobInterviewBuilder hour(String hour) {
        this.hour = hour;
        return this;
    }

    public JobInterviewBuilder employeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public JobInterviewBuilder note(String note) {
        this.note = note;
        return this;
    }

    public JobInterviewBuilder rating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public JobInterviewBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public JobInterviewDto build() {
        return new JobInterviewDto(id, date, hour, employeeId, rating, note, isActive);
    }
}
