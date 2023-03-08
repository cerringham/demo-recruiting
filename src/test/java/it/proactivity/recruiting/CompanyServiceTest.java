package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.service.CompanyService;
import it.proactivity.recruiting.utility.CompanyUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyUtility companyUtility;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void getAllCompanyTest() {
        List<CompanyDto> dtoList = companyService.getdAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getCompanyByIdTest() {
        CompanyDto companyDto = companyService.findById(1l).getBody();
        assertNotNull(companyDto);
    }

    @Test
    void checkCompanyPresenceNoMissingCompaniesPositiveTest() {

        ResponseEntity response = companyService.checkCompanyPresence();

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.OK).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void checkCompanyPresenceCompaniesNotActive() {
        companyUtility.deleteCompany("RadicalBit", "Bitrock");

        ResponseEntity response = companyService.checkCompanyPresence();

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.CREATED).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

}
