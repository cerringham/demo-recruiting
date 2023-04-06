package it.proactivity.recruiting;
import it.proactivity.recruiting.builder.LoginDtoBuilder;
import it.proactivity.recruiting.model.dto.AccountDto;
import it.proactivity.recruiting.model.dto.AccountWithRoleDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import org.junit.jupiter.api.Test;
import it.proactivity.recruiting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void addAccountPositiveTest() {
        AccountDto accountDto = new AccountDto("Vernica", "Zuniga", "veronicazunga@gmail.com",
                "veronicazunga@gmail.com", "Vero90%", true, 1l);

        ResponseEntity response = accountService.addAccount(accountDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addAccountNegativeTest() {
        AccountDto accountDto = new AccountDto("Veronica", "Zuniga", "veronicazuniga@gmail.com",
                "veronicazuniga@gmail.com", "abc", true, 1l);

        ResponseEntity response = accountService.addAccount(accountDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void loginPositiveTest() {
        LoginDto loginDto = LoginDtoBuilder.newBuilder("veronicazuniga@gmail.com").password("Vero90%").build();

        ResponseEntity response = accountService.login(loginDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void loginPositiveSettingFalseTest() {
        LoginDto loginDto = new LoginDto("veronicazuniga@gmail.com", "Vero90%");

        ResponseEntity response = accountService.login(loginDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void loginNonExistingUsernameTest() {
        LoginDto loginDto = new LoginDto("veronicauniga@gmail.com", "Vero90%");

        ResponseEntity response = accountService.login(loginDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getInformationFromUserTest() {
        Optional<AccountWithRoleDto> account = accountService.getInformationFromUser("juandavis@gmail.com");

        assertTrue(account.isPresent());

        //wrong email
        Optional<AccountWithRoleDto> account2 = accountService.getInformationFromUser("juanvis@gmail.com");

        assertTrue(account2.isEmpty());
    }

}
