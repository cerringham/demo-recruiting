package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.JobPositionStatusDto;

public class JobPositionStatusDtoBuilder {

    private String name;

    private Boolean isActive;

    private JobPositionStatusDtoBuilder(String name) {
        this.name = name;
    }

    public static JobPositionStatusDtoBuilder newBuilder(String name) {
        return new JobPositionStatusDtoBuilder(name);
    }

    public JobPositionStatusDtoBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public JobPositionStatusDto build() {
        return new JobPositionStatusDto(name, isActive);
    }
}
