package it.proactivity.recruiting.service;


import it.proactivity.recruiting.builder.CompanyBuilder;
import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.utility.CompanyUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
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

        List<Company> companyList = companyRepository.findByIsActive(true);
        if (companyList.size() == 4) {
            return ResponseEntity.ok().build();
        }
        if (companyList.size() > 4) {
            return ResponseEntity.status(400).build();
        }
        Optional<Company> fortitude = companyRepository.findByNameIgnoreCase("Fortitude");
        if (fortitude.isEmpty()) {
           Company newCompany =  CompanyBuilder.newBuilder("Fortitude").isActive(true).build();
            companyRepository.save(newCompany);
        } else if (fortitude.get().getIsActive() == false) {
            fortitude.get().setIsActive(true);
            companyRepository.save(fortitude.get());
        }
        Optional<Company> bitrock = companyRepository.findByNameIgnoreCase("Bitrock");
        if (bitrock.isEmpty()) {
            Company newCompany =  CompanyBuilder.newBuilder("Bitrock").isActive(true).build();
            companyRepository.save(newCompany);
        } else if (bitrock.get().getIsActive() == false) {
            bitrock.get().setIsActive(true);
            companyRepository.save(bitrock.get());
        }
        Optional<Company> proactivity = companyRepository.findByNameIgnoreCase("Proactivity");
        if (proactivity.isEmpty()) {
            Company newCompany =  CompanyBuilder.newBuilder("Proactivity").isActive(true).build();
            companyRepository.save(newCompany);
        } else if (proactivity.get().getIsActive() == false) {
            proactivity.get().setIsActive(true);
            companyRepository.save(proactivity.get());
        }
        Optional<Company> radicalBit = companyRepository.findByNameIgnoreCase("Radicalbit");
        if (radicalBit.isEmpty()) {
            Company newCompany =  CompanyBuilder.newBuilder("Radicalbit").isActive(true).build();
            companyRepository.save(newCompany);
        } else if (radicalBit.get().getIsActive() == false) {
            radicalBit.get().setIsActive(true);
            companyRepository.save(radicalBit.get());
        }
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity deleteCompanyById(Long id) {
        globalValidator.validateId(id);
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        companyRepository.delete(company.get());
        return ResponseEntity.ok().build();
    }
}
