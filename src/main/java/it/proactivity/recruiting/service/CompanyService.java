package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.utility.CompanyValidator;
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
    CompanyValidator companyValidator;
    public ResponseEntity<List<CompanyDto>> findAll() {

        List<Company> companyList = companyRepository.findAll();

        List<CompanyDto> dtoList = companyList.stream()
                .map(c -> new CompanyDto(c.getName(), c.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }


    public ResponseEntity<CompanyDto> findById(Long id) {

        companyValidator.validateId(id);

        Optional<Company> company = companyRepository.findById(id);

        if (company.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(new CompanyDto(company.get().getName(), company.get().getIsActive()));
    }
}
