package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.utility.CompanyUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyService {

    private static final int MAX_COMPANIES = 4;

    @Value("${recruiting.expectedCompany}")
    private List<String> expectedCompany;
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
        List<Company> companies = companyRepository.findAll();
        List<String> companyNames = companies.stream().map(Company::getName).toList();

        //Check if the companies are more than 4
        if (companies.size() > MAX_COMPANIES) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //Check if there are correct company
            if (companies.size() == MAX_COMPANIES && Boolean.FALSE.equals(companyUtility.checkCompanyNames(companyNames,
                    expectedCompany))) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }


        //If the companies are less than 4, I make the missing one with the flag set to true and set the flag to true for the
        //existence one
        if (companies.size() < MAX_COMPANIES) {
            Set<Company> missingCompanies = companyUtility.createMissingCompany(companies, expectedCompany);

            missingCompanies.forEach(c -> companyRepository.save(c));
            
            companies.forEach(c -> {
                c.setIsActive(true);
                companyRepository.save(c);
            });

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        //Retrieve any possible companies with flag false
        List<Company> companiesNotActive = companies.stream()
                .filter(c -> c.getIsActive().equals(false))
                .toList();
        /*
        If the companies are 4 and there are companies with the flag set to false , I set all the flag to true,
        else I return response ok
         */
        if (companies.size() == MAX_COMPANIES && !companiesNotActive.isEmpty()) {
            companyUtility.setIsActiveFlagToTrue(companies);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
