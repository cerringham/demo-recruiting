package it.proactivity.recruiting;

import it.proactivity.recruiting.builder.CompanyRoleDtoBuilder;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.service.CompanyRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
 class CompanyRoleServiceTest {

    @Autowired
    CompanyRoleService companyRoleService;

    @Test
    void getAllCompanyRolesTest() {
        List<CompanyRoleDto> dtoList = companyRoleService.getAll("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010").getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void insertCompanyRolePositiveTest() {
        CompanyRoleDto companyRole = CompanyRoleDtoBuilder.newBuilder("New engineer role").isActive(true).build();
        ResponseEntity response = companyRoleService.insertCompanyRole("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", companyRole);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void insertCompanyRoleNegativeTest() {
        CompanyRoleDto companyRole = CompanyRoleDtoBuilder.newBuilder("New engineer role").isActive(true).build();
        ResponseEntity response1 = companyRoleService.insertCompanyRole("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", companyRole);

        CompanyRoleDto companyRole2 = CompanyRoleDtoBuilder.newBuilder("Coo").isActive(true).build();
        ResponseEntity response2 = companyRoleService.insertCompanyRole("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", companyRole2);

        assertEquals(response1.getStatusCode(), response2.getStatusCode());
    }

    @Test
    void updateCompanyRolePositiveTest() {
        CompanyRoleDto companyRoleDto = new CompanyRoleDto(7l, "New role");
        ResponseEntity response = companyRoleService.updateCompanyRole("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", companyRoleDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateCompanyRoleNegativeTest() {
        CompanyRoleDto companyRoleDto = new CompanyRoleDto(1l, "New role");
        ResponseEntity response1 = companyRoleService.updateCompanyRole("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", companyRoleDto);

        CompanyRoleDto companyRoleDto2 = new CompanyRoleDto(3l, "Administration");
        ResponseEntity response2 = companyRoleService.updateCompanyRole("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", companyRoleDto2);

        assertEquals(HttpStatus.BAD_REQUEST, response1.getStatusCode());
        assertEquals(response1.getStatusCode(), response2.getStatusCode());
    }
}
