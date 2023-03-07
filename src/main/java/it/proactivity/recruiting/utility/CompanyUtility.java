package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CompanyDtoBuilder;
import it.proactivity.recruiting.model.dto.CompanyDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyUtility {

    public CompanyDto createCompanyDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of company dto can't be null or empty");
        }

        return CompanyDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }
}
