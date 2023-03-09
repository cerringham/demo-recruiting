package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.utility.CompanyUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        if (companiesBefore.size() > 4) {
            return ResponseEntity.badRequest().build();
        }
        companyUtility.validCompany("Fortitude");
        companyUtility.validCompany("Proactivity");
        companyUtility.validCompany("Bitrock");
        companyUtility.validCompany("RadicalBit");

        List<Company> companiesAfter = companyRepository.findByIsActive(true);
        if (companiesBefore == companiesAfter) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(201).build();
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
