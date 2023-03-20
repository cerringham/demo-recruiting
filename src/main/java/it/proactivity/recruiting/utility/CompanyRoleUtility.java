package it.proactivity.recruiting.utility;


import it.proactivity.recruiting.builder.CompanyRoleDtoBuilder;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyRoleUtility {

    @Value("${recruiting.defaultRoles}")
    private List<String> defaultRoles;

    public CompanyRoleDto createCompanyRoleDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("the parameters for creating the company role dto can't be null or empty");
        }

        return CompanyRoleDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }

    public String transformCompanyRoleName(String companyRoleName) {
        if (StringUtils.isEmpty(companyRoleName)) {
            throw new NullPointerException();
        }
        return WordUtils.capitalizeFully(companyRoleName);
    }

    public Boolean checkIfDefaultRole(String companyName) {
        return defaultRoles.contains(companyName);
    }
}
