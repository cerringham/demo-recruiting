package it.proactivity.recruiting;
import it.proactivity.recruiting.model.dto.AccountDto;
import org.junit.jupiter.api.Test;
import it.proactivity.recruiting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void addAccountPositiveTest() {
        AccountDto accountDto = new AccountDto("Veronica", "Zuniga", "veronicazuniga@gmail.com",
                "veronicazuniga@gmail.com", "Vero90%", true);

        ResponseEntity response = accountService.addAccount(accountDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addAccountNegativeTest() {
        AccountDto accountDto = new AccountDto("Veronica", "Zuniga", "veronicazuniga@gmail.com",
                "veronicazuniga@gmail.com", "abc", true);

        ResponseEntity response = accountService.addAccount(accountDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
