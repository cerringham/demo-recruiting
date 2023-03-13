package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.JobPositionDto;

public class JobPositionDtoBuilder {

    private final String title;

    private String area;

    private String description;

    private String city;

    private String region;

    private String country;

    private Boolean isActive;

    private JobPositionDtoBuilder(String title) {
        this.title = title;
    }

    public static JobPositionDtoBuilder newBuilder(String title) {
        return new JobPositionDtoBuilder(title);
    }

    public JobPositionDtoBuilder area(String area) {
        this.area = area;
        return this;
    }

    public JobPositionDtoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public JobPositionDtoBuilder city(String city) {
        this.city = city;
        return this;
    }

    public JobPositionDtoBuilder region(String region) {
        this.region = region;
        return this;
    }

    public JobPositionDtoBuilder country(String country) {
        this.country = country;
        return this;
    }

    public JobPositionDtoBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public JobPositionDto build() {
        return new JobPositionDto(title, area, description, city, region, country, isActive);
    }
}
