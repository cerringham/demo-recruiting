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

import java.util.ArrayList;
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
        List<String> allowedNames = Arrays.asList(environment.getProperty("recruiting.allowedNames").split(","));
        int maxCompanies = environment.getProperty("recruiting.maxCompanies", Integer.class);
        List<Company> actual = companyRepository.findByIsActive(true);
        List<Company> newCompanies = new ArrayList<>();
        for (String name : allowedNames) {
            Company company = companyUtility.getCompanyIfExistsOrCreate(name);
            newCompanies.add(company);
        }
        if (newCompanies.equals(actual)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }else if (!newCompanies.equals(actual)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        List<String> activeCompanies = companyUtility.getActiveCompanies();
        if (!companyUtility.onlyAllowedCompanies(activeCompanies, allowedNames, maxCompanies)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
