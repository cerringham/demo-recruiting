package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CompanyBuilder;
import it.proactivity.recruiting.builder.CompanyDtoBuilder;
import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.awt.event.ComponentAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public Set<Company> createMissingCompany(List<Company> activeCompanyList) {
        List<String> companyNames = Arrays.asList("Bitrock", "Fortitude", "Proactivity", "Radicalbit");

        Set<String> activeCompanyNames = activeCompanyList.stream()
                .map(Company::getName)
                .collect(Collectors.toSet());

        Set<Company> missingCompanies = companyNames.stream()
                .map(n -> {
                    Company company = null;
                    if (!activeCompanyNames.contains(n)) {
                        company = CompanyBuilder.newBuilder(n)
                                .isActive(true)
                                .build();

                    }
                    return company;
                }).collect(Collectors.toSet());

        return missingCompanies;
    }


    public void deleteCompany(String firstCompanyName, String secondCompanyName) {

        Optional<Long> firstCompanyId = companyRepository.findByName(firstCompanyName);

        Optional<Long> secondCompanyId = companyRepository.findByName(secondCompanyName);

        Optional<Company> firstCompany = companyRepository.findByIdAndIsActive(firstCompanyId.get(), true);

        Optional<Company> secondCompany = companyRepository.findByIdAndIsActive(firstCompanyId.get(), true);

        firstCompany.get().setIsActive(false);
        secondCompany.get().setIsActive(false);

        List<Company> companies = Arrays.asList(firstCompany.get(), secondCompany.get());
        companyRepository.saveAll(companies);
    }
}
