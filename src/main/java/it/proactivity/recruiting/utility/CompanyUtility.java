package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CompanyBuilder;
import it.proactivity.recruiting.builder.CompanyDtoBuilder;
import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.stream.Collectors;

@Component
public class CompanyUtility {

    @Autowired
    CompanyRepository companyRepository;

    public CompanyDto createCompanyDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of company dto can't be null or empty");
        }

        return CompanyDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }

    public Boolean checkCompanyNames(List<String> realCompany, List<String> expectedCompanies) {
        for (String n : expectedCompanies) {
            if (!realCompany.contains(n)) {
                return false;
            }
        }
        return true;
    }

    public Set<Company> createMissingCompany(List<Company> activeCompanyList, List<String> expectedCompanyNames) {


        Set<String> activeCompanyNames = activeCompanyList.stream()
                .map(Company::getName)
                .collect(Collectors.toSet());


        Set<Company> missingCompany = new HashSet<>();
        for (String n : expectedCompanyNames) {
            if (!activeCompanyNames.contains(n)) {
                Company company = CompanyBuilder.newBuilder(n)
                        .isActive(true)
                        .build();
                missingCompany.add(company);
            }
        }
        return missingCompany;
    }

    public void setIsActiveFlagToTrue(List<Company> companies) {
        companies.stream()
                .forEach(c -> {
                    c.setIsActive(true);
                    companyRepository.save(c);
                });
    }

}
