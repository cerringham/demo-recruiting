package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobInterviewStatusDtoBuilder;
import it.proactivity.recruiting.model.dto.JobInterviewStatusDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class JobInterviewStatusUtility {

    public JobInterviewStatusDto createJobInterviewStatusDto(String name, String description, Boolean isActive) {

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(description) || isActive == null) {
            throw new IllegalArgumentException("the parameters for the creation of job interview status dto can't be " +
                    "null or empty");
        }
        return JobInterviewStatusDtoBuilder.newBuilder(name)
                .description(description)
                .isActive(isActive)
                .build();
    }
}
