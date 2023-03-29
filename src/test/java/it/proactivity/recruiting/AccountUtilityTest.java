package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.AccountInformationDto;
import it.proactivity.recruiting.utility.AccountUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@SpringBootTest
class AccountUtilityTest {

    @Autowired
    AccountUtility accountUtility;

    @Test
    void retrieveAccountInformationTest() {

        Optional<AccountInformationDto> dto = accountUtility.getAccountInformation("alessio.cassarino@proactivity.it");
        dto.ifPresent(accountInformationDto -> assertTrue(accountInformationDto.getAccountName().equals("Alessio")));
    }

    @Test
    void retrieveAccountInformationNegativeTest() {

        Optional<AccountInformationDto> dto = accountUtility.getAccountInformation("maurizio.cassarino@proactivity.it");
        assertTrue(dto.isEmpty());
    }
}
