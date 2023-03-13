package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.ExpertiseDto;

public class ExpertiseDtoBuilder {

    private final String name;

    private Boolean isActive;

    private ExpertiseDtoBuilder(String name) {
        this.name = name;
    }

    public static ExpertiseDtoBuilder newBuilder(String name) {
        return new ExpertiseDtoBuilder(name);
    }

    public ExpertiseDtoBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public ExpertiseDto build() {
        return new ExpertiseDto(name, isActive);
    }
}
