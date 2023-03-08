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


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyService {

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
        List<Company> companiesWithFlagFalse = companyRepository.findByIsActive(false);

        if (companies.size() > 4) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //Controllo che ci siano le corrette Company nel db
        if (companies.size() == 4) {
            if (!companyUtility.checkCompanyNames(companyNames, EXPECTED_COMPANIES)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }

        //Se le company sono meno di 4 creo quelle mancanti con il flag a true e setto quelle gia esistenti a true
        if (companies.size() < 4) {
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

        /*Se le company sono 4 e ci sono delle company a false setto il flag a true a tutte le company altrimenti,
        ritorno una risposta 200
         */
        if (companies.size() == 4 && companiesWithFlagFalse.size() != 0) {
            companyUtility.setIsActiveFlagToTrue(companies);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
