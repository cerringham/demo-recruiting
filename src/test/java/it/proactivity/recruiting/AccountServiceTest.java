package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AddAccountDto;
import it.proactivity.recruiting.repository.AccountRepository;
import it.proactivity.recruiting.service.AccountService;
import org.junit.Test;
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

    @Test
    public void addAccountPositiveTest() {
        AddAccountDto dto = new AddAccountDto("Alessio", "Cassarino", "alessio.cassarino@proactivity.it",
                "alessio.cassarino@proactivity.it", "Proactivity1!");

        ResponseEntity response = accountService.addAccount(dto);

        Optional<Account> account = accountRepository.findByUsername(dto.getUsername());
        if (account.isPresent()) {
            assertTrue(account.get().getName().equals("Alessio"));
            assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());
        }
    }
}
