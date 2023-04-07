package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.AccessTokenDto;
import it.proactivity.recruiting.model.dto.AccountDto;
import it.proactivity.recruiting.service.AccessTokenService;
import it.proactivity.recruiting.utility.AccessTokenUtility;
import it.proactivity.recruiting.utility.GlobalUtility;
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
    GlobalUtility globalUtility;

    @Test
    void checkAccessTokenUnauthorizedTest() {

        AccountDto accountDto = new AccountDto("Veronica", "Zuniga", "veronicazuniga@gmail.com",
                "veronicazuniga@gmail.com", "Vero90%", true, 3l);

        AccessTokenDto accessTokenDto = new AccessTokenDto(2l, LocalTime.of(10,54), accountDto, true,
                "ybQXpmgdhrhw.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680597872859");
        ResponseEntity response = accessTokenService.checkAccessToken(accessTokenDto);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void checkAccessTokenPositiveTest() {

        AccountDto accountDto = new AccountDto("Veronica", "Zuniga", "veronicazuniga@gmail.com",
                "veronicazuniga@gmail.com", "Vero90%", true, 2l);

        AccessTokenDto accessTokenDto = new AccessTokenDto(4l, LocalTime.of(16,57), accountDto, true,
                "adUlLPONvMdg.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680619670726");
        ResponseEntity response = accessTokenService.checkAccessToken(accessTokenDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void checkIfRoleIsAuthorizedTest() {
        Set<String> authorizedRoleNames = new HashSet<>();
        authorizedRoleNames.add("admin");
        authorizedRoleNames.add("hr");
        assertTrue(globalUtility.checkIfTokenAndAccountAreValid("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", authorizedRoleNames));
    }
}
