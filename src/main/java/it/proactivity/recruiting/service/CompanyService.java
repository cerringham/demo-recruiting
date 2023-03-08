package it.proactivity.recruiting.service;


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
import java.util.Set;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    CompanyUtility companyUtility;

    public ResponseEntity<List<CompanyDto>> getdAll() {

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
        List<Company> activeCompanyList = companyRepository.findByIsActive(true);

        List<Company> notActiveCompanyList = companyRepository.findByIsActive(false);

        if (activeCompanyList.size() > 4) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (activeCompanyList.size() < 4 && !notActiveCompanyList.isEmpty()) {
            notActiveCompanyList.stream()
                    .forEach(c -> {
                        c.setIsActive(true);
                        companyRepository.save(c);
                    });

            Set<Company> missingCompanies = companyUtility.createMissingCompany(activeCompanyList);
            missingCompanies.stream()
                    .forEach(c -> companyRepository.save(c));

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        if (!notActiveCompanyList.isEmpty()) {
            notActiveCompanyList.stream()
                    .forEach(c -> {
                        c.setIsActive(true);
                        companyRepository.save(c);
                    });
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        if (activeCompanyList.size() < 4) {
            Set<Company> missingCompanies = companyUtility.createMissingCompany(activeCompanyList);
            missingCompanies.stream()
                    .forEach(c -> companyRepository.save(c));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
