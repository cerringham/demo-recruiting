package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.ExpertiseDto;

public class ExpertiseDtoBuilder {

    private Long id;

    private String name;

    private Boolean isActive;

    private ExpertiseDtoBuilder(String name) {
        this.name = name;
    }

    public static ExpertiseDtoBuilder newBuilder(String name) {
        return new ExpertiseDtoBuilder(name);
    }
    public ExpertiseDtoBuilder id(Long id) {
        this.id = id;
        return this;
    }
    public ExpertiseDtoBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public ExpertiseDto build() {
        return new ExpertiseDto(name, isActive);
    }
}
