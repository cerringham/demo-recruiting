package it.proactivity.recruiting;

import it.proactivity.recruiting.methods.AccountMethods;
import it.proactivity.recruiting.model.dto.AccountInformationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class AccountMethodsTest {

    @Autowired
    AccountMethods accountMethods;

    @Test
    void retrieveAccountInformationTest() {

        Optional<AccountInformationDto> dto = accountMethods.retrieveAccountInformation("alessio.cassarino@proactivity.it");
        if (dto.isPresent()) {
            assertTrue(dto.get().getAccountName().equals("Alessio"));
        }
    }

    @Test
    void retrieveAccountInformationNegativeTest() {

        Optional<AccountInformationDto> dto = accountMethods.retrieveAccountInformation("maurizio.cassarino@proactivity.it");
        assertTrue(dto.isEmpty());
    }
}
