package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AddAccountDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.AccountRepository;
import it.proactivity.recruiting.service.AccountService;
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

    @Autowired
    AccessTokenRepository accessTokenRepository;

    private static final ResponseEntity POSITIVE_RESPONSE = ResponseEntity.status(HttpStatus.OK).build();

    private static final ResponseEntity BAD_REQUEST_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    @Test
    void addAccountPositiveTest() {
        AddAccountDto dto = new AddAccountDto("Ciccio", "Graziani", "ciccio.graziani@proactivity.it",
                "ciccio.graziani@proactivity.it", "Graziani1!");

        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = accountService.addAccount(token.get(), dto);

        Optional<Account> account = accountRepository.findByUsername(dto.getUsername());
        if (account.isPresent()) {
            assertTrue(account.get().getName().equals("Ciccio"));
            assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());
        }
    }

    @Test
    void loginPositiveTest() {
        LoginDto dto = new LoginDto("luigi.cerrato@proactivity.it", "Password1!");

        ResponseEntity response = accountService.login(dto);

        assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());
    }

    @Test
    void addAccountShortPasswordNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "emanuele.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "Emm1!");

        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = accountService.addAccount(token.get(), dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void addAccountLongPasswordNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "emanuele.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "Emanuele1!!!!");

        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = accountService.addAccount(token.get(), dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void addAccountPasswordWithoutUpperCaseLetterNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "emanuele.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "emanuele1!");

        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = accountService.addAccount(token.get(), dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void addAccountPasswordWithoutNumberNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "emanuele.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "Emanuele!");

        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = accountService.addAccount(token.get(), dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void addAccountPasswordWithoutSpecialCharNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "emanuele.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "Emanuele1");

        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = accountService.addAccount(token.get(), dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void addAccountEmailAndUsernameNotSameNegativeTest() {
        AddAccountDto dto = new AddAccountDto("Emanuele", "Caracciolo", "Emmma.caracciolo@proactivity.it",
                "emanuele.caracciolo@proactivity.it", "Emanuele1!");

        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        ResponseEntity response = accountService.addAccount(token.get(), dto);

        assertEquals(BAD_REQUEST_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    private Optional<String> getToken(String accountUsername, String password) {
        LoginDto dto = new LoginDto(accountUsername, password);
        accountService.login(dto);
        return accessTokenRepository.findLatestTokenValueByUsername(accountUsername);
    }
}
