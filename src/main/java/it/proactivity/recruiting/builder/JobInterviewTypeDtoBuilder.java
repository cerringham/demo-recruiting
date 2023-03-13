package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.JobInterviewTypeDto;

public class JobInterviewTypeDtoBuilder {

    private final String name;

    private JobInterviewTypeDtoBuilder(String name) {
        this.name = name;
    }

    public static JobInterviewTypeDtoBuilder newBuilder(String name) {
        return new JobInterviewTypeDtoBuilder(name);
    }

    public JobInterviewTypeDto build() {
        return new JobInterviewTypeDto(name);
    }
}
