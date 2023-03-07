package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobInterviewTypeDtoBuilder;
import it.proactivity.recruiting.model.dto.JobInterviewTypeDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class JobInterviewTypeUtility {

    public JobInterviewTypeDto createJobInterviewTypeDto(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("The name can't be null or empty for the creation of JobInterviewTypeDto");
        }

        return JobInterviewTypeDtoBuilder.newBuilder(name).build();
    }
}
