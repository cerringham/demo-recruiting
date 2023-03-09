package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CompanyBuilder;
import it.proactivity.recruiting.builder.CompanyDtoBuilder;
import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public Company validCompany(String companyName) {
        Optional<Company> company = companyRepository.findByName(companyName);
        if (company.isPresent()) {
            if (!company.get().getIsActive()) {
                company.get().setIsActive(true);
                companyRepository.save(company.get());
                return company.get();
            }
            return company.get();
        } else {
            Company newCompany = CompanyBuilder.newBuilder(companyName).isActive(true).build();
            companyRepository.save(newCompany);
            return newCompany;
        }
    }
}
