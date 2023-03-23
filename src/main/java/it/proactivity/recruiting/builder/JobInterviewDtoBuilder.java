package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.JobInterviewDto;

public class JobInterviewDtoBuilder {

    private final String date;

    private String hour;

    private String place;

    private Integer rating;

    private String note;

    private Boolean isActive;

    private JobInterviewDtoBuilder(String date) {
        this.date = date;
    }

    public static JobInterviewDtoBuilder newBuilder(String date) {
        return new JobInterviewDtoBuilder(date);
    }

    public JobInterviewDtoBuilder hour(String hour) {
        this.hour = hour;
        return this;
    }

    public JobInterviewDtoBuilder place(String place) {
        this.place = place;
        return this;
    }

    public JobInterviewDtoBuilder note(String note) {
        this.note = note;
        return this;
    }

    public JobInterviewDtoBuilder rating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public JobInterviewDtoBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public JobInterviewDto build() {
        return new JobInterviewDto(date, hour, place, rating, note, isActive);
    }
}
