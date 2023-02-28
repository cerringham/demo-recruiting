package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.CompanyDtoBuilder;
import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
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

    public ResponseEntity<List<CompanyDto>> getAll() {

        List<Company> companyList = companyRepository.findByIsActive(true);

        List<CompanyDto> dtoList = companyList.stream()
                .map(c -> createCompanyDto(c.getName(), c.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CompanyDto> findById(Long id) {

        globalValidator.validateId(id);

        Optional<Company> company = companyRepository.findByIdAndIsActive(id, true);

        if (company.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createCompanyDto(company.get().getName(), company.get().getIsActive()));
    }

    private CompanyDto createCompanyDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of company dto can't be null or empty");
        }

        return CompanyDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }

    public ResponseEntity<CompanyDto> deleteById(Long id) {
        globalValidator.validateId(id);
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        company.get().setIsActive(false);
        companyRepository.save(company.get());
        CompanyDto companyDto = createCompanyDto(company.get().getName(), company.get().getIsActive());
        return ResponseEntity.ok(companyDto);
    }
}
