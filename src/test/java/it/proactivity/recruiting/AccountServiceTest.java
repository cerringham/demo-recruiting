package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AddAccountDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.repository.AccountRepository;
import it.proactivity.recruiting.service.AccountService;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
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

    @Autowired
    AccountRepository accountRepository;

    private static final ResponseEntity POSITIVE_RESPONSE = ResponseEntity.status(HttpStatus.OK).build();

    private static final ResponseEntity BAD_REQUEST_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    @Test
    void addAccountPositiveTest() {
        AddAccountDto dto = new AddAccountDto("Ciccio", "Graziani", "ciccio.graziani@proactivity.it",
                "ciccio.graziani@proactivity.it", "Graziani1!");

        ResponseEntity response = accountService.addAccount(dto);

        Optional<Account> account = accountRepository.findByUsername(dto.getUsername());
        if (account.isPresent()) {
            assertTrue(account.get().getName().equals("Ciccio"));
            assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());
        }
    }

    @Test
    void loginPositiveTest() {
        LoginDto dto = new LoginDto("ciccio.graziani@proactivity.it", "Graziani1!");

        ResponseEntity response = accountService.login(dto);

        assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());
    }

    @Test
    void addAccountShortPasswordNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "emanuele.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "Emm1!");

        ResponseEntity response = accountService.addAccount(dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void addAccountLongPasswordNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "emanuele.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "Emanuele1!!!!");

        ResponseEntity response = accountService.addAccount(dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void addAccountPasswordWithoutUpperCaseLetterNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "emanuele.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "emanuele1!");

        ResponseEntity response = accountService.addAccount(dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void addAccountPasswordWithoutNumberNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "emanuele.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "Emanuele!");

        ResponseEntity response = accountService.addAccount(dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void addAccountPasswordWithoutSpecialCharNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "emanuele.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "Emanuele1");

        ResponseEntity response = accountService.addAccount(dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void addAccountEmailAndUsernameNotSameNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "Emmma.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "Emanuele1!");

        ResponseEntity response = accountService.addAccount(dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }
}
