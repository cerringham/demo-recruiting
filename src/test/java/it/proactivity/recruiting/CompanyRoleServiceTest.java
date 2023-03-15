package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.service.CompanyRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
class CompanyRoleServiceTest {

    private final ResponseEntity POSITIVE_RESPONSE = ResponseEntity.status(HttpStatus.OK).build();

    private final ResponseEntity BAD_REQUEST_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    private final ResponseEntity FOUND_RESPONSE = ResponseEntity.status(HttpStatus.FOUND).build();

    private final ResponseEntity FORBIDDEN_RESPONSE = ResponseEntity.status(HttpStatus.FORBIDDEN).build();

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

    @Test
    void insertCompanyRolePositiveTest() {

        ResponseEntity response = companyRoleService.insertCompanyRole("soap developer");

        assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void deleteCompanyRolePositiveTest() {
        ResponseEntity response = companyRoleService.deleteCompanyRole(13L);
        assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());
    }

    @Test
    void updateCompanyRolePositiveTest() {
        ResponseEntity response = companyRoleService.updateCompanyRole(14L, "backend developer java");
        assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());
    }

    @Test
    void updateCompanyRoleNameAlreadyExistsNegativeTest() {
        ResponseEntity response = companyRoleService.updateCompanyRole(14L, "Hr");
        assertEquals(FORBIDDEN_RESPONSE.getStatusCode(), response.getStatusCode());
    }

    @Test
    void updateCompanyRoleDefaultCompanyRoleNegativeTest() {
        ResponseEntity response = companyRoleService.updateCompanyRole(10L, "Cooo");
        assertEquals(FORBIDDEN_RESPONSE.getStatusCode(), response.getStatusCode());
    }

    @Test
    void deleteDefaultCompanyRoleNegativeTest() {
        ResponseEntity response = companyRoleService.deleteCompanyRole(12L);
        assertEquals(FORBIDDEN_RESPONSE.getStatusCode(), response.getStatusCode());
    }

    @Test
    void insertCompanyRoleAlreadyExistsNegativeTest() {
        ResponseEntity response = companyRoleService.insertCompanyRole("frontend engineer");

        assertEquals(FOUND_RESPONSE.getStatusCode(), response.getStatusCode());
    }

    @Test
    void insertCompanyRoleNullNameNegativeTest() {
        ResponseEntity response = companyRoleService.insertCompanyRole(null);
        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());
    }

    @Test
    void insertCompanyRoleEmptyNameNegativeTest() {
        ResponseEntity response = companyRoleService.insertCompanyRole("");
        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());
    }
}
