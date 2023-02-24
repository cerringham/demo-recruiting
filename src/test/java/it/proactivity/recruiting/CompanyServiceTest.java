package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

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
}
