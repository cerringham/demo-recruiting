package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CompanyBuilder;
import it.proactivity.recruiting.builder.CompanyDtoBuilder;
import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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

    public Company getCompanyIfExistsOrCreate(String companyName) {
        Optional<Company> company = companyRepository.findByNameAndIsActive(companyName, true);
        if (company.isPresent()) {
            if (!company.get().getIsActive()) {
                company.get().setIsActive(true);
                return companyRepository.save(company.get());
            }
            return company.get();
        } else {
            Company newCompany = CompanyBuilder.newBuilder(companyName).isActive(true).build();
            return companyRepository.save(newCompany);
        }
    }

    public void setCompanyFalseByName(String name) {
        Optional<Company> company = companyRepository.findByName(name);
        if (company.isPresent()) {
            company.get().setIsActive(false);
            companyRepository.save(company.get());
        }
    }

    public List<String> getActiveCompanies() {
        return companyRepository.findByIsActive(true).stream()
                .map(Company::getName)
                .collect(Collectors.toList());
    }

    public boolean onlyAllowedCompanies(List<String> active, List<String> allowedNames, int maxCompanies) {
        List<String> notAllowed = active.stream().filter(name -> !allowedNames.contains(name)).collect(Collectors.toList());
        if (!notAllowed.isEmpty()) {
            List<Company> companiesToDelete = companyRepository.findByNameList(notAllowed);
            companyRepository.deleteAll(companiesToDelete);
        }
        return active.containsAll(allowedNames);
    }
}
