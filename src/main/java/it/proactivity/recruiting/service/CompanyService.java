package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.utility.CompanyUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    CompanyUtility companyUtility;

    @Autowired
    private Environment environment;

    public ResponseEntity<List<CompanyDto>> getAll() {

        List<Company> companyList = companyRepository.findByIsActive(true);

        List<CompanyDto> dtoList = companyList.stream()
                .map(c -> companyUtility.createCompanyDto(c.getName(), c.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CompanyDto> findById(Long id) {

        globalValidator.validateId(id);

        Optional<Company> company = companyRepository.findByIdAndIsActive(id, true);

        if (company.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(companyUtility.createCompanyDto(company.get().getName(), company.get().getIsActive()));
    }
    public ResponseEntity checkCompanyPresence() {
        List<Company> companiesBefore = companyRepository.findByIsActive(true);
        int maxCompanies = environment.getProperty("recruiting.maxCompanies", Integer.class);
        if (companiesBefore.size() > maxCompanies) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<String> allowedNames = Arrays.asList(environment.getProperty("recruiting.allowedNames").split(","));
        for (String name : allowedNames) {
            companyUtility.validCompany(name);
        }
        List<Company> companiesAfter = companyRepository.findByIsActive(true);
        List<String> actualCompanies = companiesAfter.stream()
                .map(Company::getName)
                .toList();
        if (!actualCompanies.containsAll(allowedNames) || actualCompanies.size() != 4) {
            List<String> notAllowed = actualCompanies.stream().filter(name -> !allowedNames.contains(name)).toList();
            if (!notAllowed.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (companiesBefore.equals(companiesAfter)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    public ResponseEntity deleteCompanyByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        Optional<Company> company = companyRepository.findByName(name);
        if (company.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        companyRepository.delete(company.get());
        return ResponseEntity.ok().build();
    }
}
