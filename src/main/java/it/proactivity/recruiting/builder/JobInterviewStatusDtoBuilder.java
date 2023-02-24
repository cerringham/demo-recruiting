package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.JobInterviewStatusDto;

public class JobInterviewStatusDtoBuilder {

    private String name;

    private String description;

    private Boolean isActive;

    private JobInterviewStatusDtoBuilder(String name) {
        this.name = name;
    }

    public static JobInterviewStatusDtoBuilder newBuilder(String name) {
        return new JobInterviewStatusDtoBuilder(name);
    }

    public JobInterviewStatusDtoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public JobInterviewStatusDtoBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public JobInterviewStatusDto build() {
        return new JobInterviewStatusDto(name, description, isActive);
    }
}
