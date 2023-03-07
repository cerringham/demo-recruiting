package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CompanyRoleDtoBuilder;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyRoleUtility {

    public CompanyRoleDto createCompanyRoleDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("the parameters for creating the company role dto can'y be null or empty");
        }

        return CompanyRoleDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }
}
