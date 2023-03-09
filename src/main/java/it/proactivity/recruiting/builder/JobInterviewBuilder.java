package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.JobInterviewDto;

public class JobInterviewBuilder {

    private final String date;

    private String hour;

    private String place;

    private Integer rating;

    private String note;

    private Boolean isActive;

    private JobInterviewBuilder(String date) {
        this.date = date;
    }

    public static JobInterviewBuilder newBuilder(String date) {
        return new JobInterviewBuilder(date);
    }

    public JobInterviewBuilder hour(String hour) {
        this.hour = hour;
        return this;
    }

    public JobInterviewBuilder place(String place) {
        this.place = place;
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
        return new JobInterviewDto(date, hour, place, rating, note, isActive);
    }
}
