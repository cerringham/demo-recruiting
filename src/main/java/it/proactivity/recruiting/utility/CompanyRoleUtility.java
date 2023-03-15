package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CompanyRoleBuilder;
import it.proactivity.recruiting.builder.CompanyRoleDtoBuilder;
import it.proactivity.recruiting.model.CompanyRole;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyRoleUtility {

    public CompanyRoleDto createCompanyRoleDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("the parameters for creating the company role dto can't be null or empty");
        }

        return CompanyRoleDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }

    public Boolean validParameters(CompanyRoleDto companyRoleDto) {
        if (StringUtils.isEmpty(companyRoleDto.getName()) || companyRoleDto.getIsActive() == null) {
            return false;
        }
        String capitalize = WordUtils.capitalizeFully(companyRoleDto.getName());
        if (capitalize.equals(companyRoleDto.getName())) {
            return true;
        }
        return false;
    }

    public CompanyRole createCompanyRoleFromDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("the parameters for creating the company role can't be null or empty");
        }
        return CompanyRoleBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }
}
