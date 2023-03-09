package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.service.CompanyRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
 class CompanyRoleServiceTest {

    @Autowired
    CompanyRoleService companyRoleService;

    @Test
    void getAllCompanyRolesTest() {
        List<CompanyRoleDto> dtoList = companyRoleService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getCompanyRoleByIdTest() {
        CompanyRoleDto dto = companyRoleService.findById(1L).getBody();
        assertNotNull(dto);
    }
}
