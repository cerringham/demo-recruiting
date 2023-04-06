package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AccessTokenDto;
import it.proactivity.recruiting.model.dto.AccountDto;
import it.proactivity.recruiting.service.AccessTokenService;
import it.proactivity.recruiting.utility.AccessTokenUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
public class AccessTokenServiceTest {

    @Autowired
    AccessTokenService accessTokenService;

    @Autowired
    AccessTokenUtility accessTokenUtility;

    /*@Test
    void checkAccessTokenUnauthorizedTest() {

        AccountDto accountDto = new AccountDto("Veronica", "Zuniga", "veronicazuniga@gmail.com",
                "veronicazuniga@gmail.com", "Vero90%", true);

        AccessTokenDto accessTokenDto = new AccessTokenDto(2l, LocalTime.of(10,54), accountDto, true,
                "ybQXpmgdhrhw.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680597872859");
        ResponseEntity response = accessTokenService.checkAccessToken(accessTokenDto);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }*/

    /*@Test
    void checkAccessTokenPositiveTest() {

        AccountDto accountDto = new AccountDto("Veronica", "Zuniga", "veronicazuniga@gmail.com",
                "veronicazuniga@gmail.com", "Vero90%", true);

        AccessTokenDto accessTokenDto = new AccessTokenDto(4l, LocalTime.of(16,57), accountDto, true,
                "adUlLPONvMdg.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680619670726");
        ResponseEntity response = accessTokenService.checkAccessToken(accessTokenDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }*/


    @Test
    void checkIfTokenIsActiveTest() {
        assertTrue(accessTokenUtility.checkIfTokenIsActive("ZGnlAwlIYiEJ.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680791292595"));
    }

    @Test
    void checkIfTokenIsActiveNegativeTest() {
        assertFalse(accessTokenUtility.checkIfTokenIsActive("ZGnlAwlIYiEJ.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680791292595"));
    }

    @Test
    void getAccountFromTokenTest() {
        Account account = accessTokenUtility.getAccountFromToken("ZGnlAwlIYiEJ.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680791292595");

        System.out.println(account.toString());
        assertTrue(account != null);
    }

    @Test
    void checkIfRoleIsAuthorizedTest() {
        Account account = accessTokenUtility.getAccountFromToken("ZGnlAwlIYiEJ.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680791292595");
        Set<String> authorizedRoleNames = new HashSet<>();
        authorizedRoleNames.add("admin");
        authorizedRoleNames.add("hr");
        assertTrue(accessTokenUtility.checkIfRoleIsAuthorized(account, authorizedRoleNames));
    }
}
