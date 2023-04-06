package it.proactivity.recruiting;

import it.proactivity.recruiting.builder.CompanyRoleDtoBuilder;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.service.AccountService;
import it.proactivity.recruiting.service.CompanyRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
 class CompanyRoleServiceTest {

    @Autowired
    CompanyRoleService companyRoleService;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    AccountService accountService;

    @Test
    void getAllCompanyRolesTest() {
        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        List<CompanyRoleDto> dtoList = companyRoleService.getAll(token.get()).getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void insertCompanyRolePositiveTest() {
        CompanyRoleDto companyRole = CompanyRoleDtoBuilder.newBuilder("New engineer role").isActive(true).build();
        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = companyRoleService.insertCompanyRole(token.get(), companyRole);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void insertCompanyRoleNegativeTest() {
        CompanyRoleDto companyRole = CompanyRoleDtoBuilder.newBuilder("New engineer role").isActive(true).build();
        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response1 = companyRoleService.insertCompanyRole(token.get(), companyRole);

        CompanyRoleDto companyRole2 = CompanyRoleDtoBuilder.newBuilder("Coo").isActive(true).build();
        ResponseEntity response2 = companyRoleService.insertCompanyRole(token.get(), companyRole2);

        assertEquals(response1.getStatusCode(), response2.getStatusCode());
    }

    @Test
    void updateCompanyRolePositiveTest() {
        CompanyRoleDto companyRoleDto = new CompanyRoleDto(7l, "New role");
        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = companyRoleService.updateCompanyRole(token.get(), companyRoleDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateCompanyRoleNegativeTest() {
        CompanyRoleDto companyRoleDto = new CompanyRoleDto(1l, "New role");
        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response1 = companyRoleService.updateCompanyRole(token.get(), companyRoleDto);

        CompanyRoleDto companyRoleDto2 = new CompanyRoleDto(3l, "Administration");
        ResponseEntity response2 = companyRoleService.updateCompanyRole(token.get(), companyRoleDto2);

        assertEquals(HttpStatus.BAD_REQUEST, response1.getStatusCode());
        assertEquals(response1.getStatusCode(), response2.getStatusCode());
    }

    private Optional<String> getToken(String accountUsername, String password) {
        LoginDto dto = new LoginDto(accountUsername, password);
        accountService.login(dto);
        return accessTokenRepository.findLatestTokenValueByUsername(accountUsername);
    }
}
