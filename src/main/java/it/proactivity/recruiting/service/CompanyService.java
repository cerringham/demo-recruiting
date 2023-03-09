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


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyService {

    @Value("${recruiting.maxCompanies}")
    private int maxCompanies;
    private static List<String> EXPECTED_COMPANIES = Arrays.asList("Bitrock", "Fortitude", "Proactivity", "Radicalbit");
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
        List<Company> companies = companyRepository.findAll();
        List<String> companyNames = companies.stream().map(Company::getName).toList();

        //Check if the companies are more than 4
        if (companies.size() > maxCompanies) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //Check if there are correct company
        if (companies.size() == maxCompanies) {
            if (!companyUtility.checkCompanyNames(companyNames, EXPECTED_COMPANIES)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }

        //If the companies are less than 4, i make the missing one with the flag set to true and set the flag to true for the
        //existence one
        if (companies.size() < maxCompanies) {
            Set<Company> missingCompanies = companyUtility.createMissingCompany(companies, EXPECTED_COMPANIES);

            missingCompanies.stream().forEach(c -> {
                companyRepository.save(c);
            });
            companies.stream().forEach(c -> {
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
        If the companies are 4 and there are companies with the flag set to false , i set all the flag to true,
        else i return response ok
         */
        if (companies.size() == maxCompanies && companiesNotActive.size() != 0) {
            companyUtility.setIsActiveFlagToTrue(companies);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
