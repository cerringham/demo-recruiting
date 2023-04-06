package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.service.AccountService;
import it.proactivity.recruiting.service.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
 class CompanyServiceTest {

    @Autowired
    CompanyService companyService;


    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    AccountService accountService;


    @Test
    void getAllCompanyTest() {
        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        List<CompanyDto> dtoList = companyService.getAll(token.get()).getBody();
        assertNotEquals(dtoList.size() , 0);
    }

    @Test
    void getCompanyByIdTest() {
        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        CompanyDto companyDto = companyService.findById(token.get(), 1L).getBody();
        assertNotNull(companyDto);
    }

    @Test
    void checkCompanyPresencePositiveTestStatus200() {

        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = companyService.checkCompanyPresence(token.get());

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.OK).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void checkCompanyPresenceLogicalDeleteTestStatus201() {
        logicalDeleteCompany("RadicalBit");
        logicalDeleteCompany("Bitrock");

        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = companyService.checkCompanyPresence(token.get());

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.CREATED).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void checkCompanyPresenceRealDeleteTestStatus201() {
        deleteCompany("Proactivity");
        deleteCompany("Bitrock");

        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = companyService.checkCompanyPresence(token.get());

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.CREATED).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    private void logicalDeleteCompany(String name) {
        if (!StringUtils.isEmpty(name)) {
            Optional<Company> company = companyRepository.findByName(name);

            if (company.isPresent()) {
                company.get().setIsActive(false);
                companyRepository.save(company.get());
            }
        }

    }

    private void deleteCompany(String name) {
        if (!StringUtils.isEmpty(name)) {
            Optional<Company> company = companyRepository.findByName(name);

            company.ifPresent(value -> companyRepository.delete(value));
        }
    }

    private Optional<String> getToken(String accountUsername, String password) {
        LoginDto dto = new LoginDto(accountUsername, password);
        accountService.login(dto);
        return accessTokenRepository.findLatestTokenValueByUsername(accountUsername);
    }
}
