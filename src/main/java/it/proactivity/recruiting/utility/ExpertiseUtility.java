package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.ExpertiseDtoBuilder;
import it.proactivity.recruiting.model.dto.ExpertiseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ExpertiseUtility {

    public ExpertiseDto createExpertiseDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("the parameters for creating the expertise dto can't be null or empty");
        }

        return ExpertiseDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }
}
