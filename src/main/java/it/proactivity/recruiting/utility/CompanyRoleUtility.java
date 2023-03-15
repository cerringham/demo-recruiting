package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CompanyRoleBuilder;
import it.proactivity.recruiting.builder.CompanyRoleDtoBuilder;
import it.proactivity.recruiting.model.CompanyRole;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.repository.CompanyRoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CompanyRoleUtility {

    @Autowired
    CompanyRoleRepository companyRoleRepository;

    public CompanyRoleDto createCompanyRoleDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("the parameters for creating the company role dto can't be null or empty");
        }

        return CompanyRoleDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }

    public Boolean checkDefaultCompanyRoleExistence(String name) {
        String capitalizeName = WordUtils.capitalizeFully(name);

        List<CompanyRole> defaultCompanyRoles = companyRoleRepository.findByIsDefault(true);

        List<String> companyRoleNames = defaultCompanyRoles.stream()
                .map(CompanyRole::getName)
                .toList();

        if (companyRoleNames.contains(capitalizeName)) {
            throw new IllegalArgumentException("Company Role already exists");
        }

        return true;
    }

    public CompanyRole createCompanyRole(String name) {
        String capitalizeName = WordUtils.capitalizeFully(name);
        return CompanyRoleBuilder.newBuilder(capitalizeName)
                .isActive(true)
                .build();
    }
}
