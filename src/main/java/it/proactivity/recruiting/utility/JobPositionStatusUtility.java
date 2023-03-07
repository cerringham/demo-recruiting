package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobPositionStatusDtoBuilder;
import it.proactivity.recruiting.model.dto.JobPositionStatusDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class JobPositionStatusUtility {

    public JobPositionStatusDto createJobPositionStatusDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of JobPositionStatusDto can't be null or empty");
        }

        return JobPositionStatusDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }
}
