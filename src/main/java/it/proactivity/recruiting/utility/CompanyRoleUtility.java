package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CompanyRoleBuilder;
import it.proactivity.recruiting.builder.CompanyRoleDtoBuilder;
import it.proactivity.recruiting.model.CompanyRole;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.repository.CompanyRoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public String validCompanyRole(String companyRoleName) {
        if (StringUtils.isEmpty(companyRoleName)) {
            throw new NullPointerException();
        }
        String name = WordUtils.capitalizeFully(companyRoleName);
        return name;
    }

    public Boolean checkIfDefaultRole(String companyName) {
        if (defaultRoles.contains(companyName)) {
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
