package it.proactivity.recruiting;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import static org.junit.Assert.*;
@SpringBootTest
public class AccessTokenServiceTest {

    private static final ResponseEntity IM_USED_RESPONSE = ResponseEntity.status(HttpStatus.IM_USED).build();

    private static final ResponseEntity GONE_RESPONSE = ResponseEntity.status(HttpStatus.GONE).build();

    @Autowired
    AccessTokenService accessTokenService;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Test
    void checkAccessTokenPositiveTest() {

        Optional<AccessToken> accessToken = accessTokenRepository.findByValue("NsPdhfQDZAPR.Y2ljY2lvLmdyYXppYW5pQHByb2FjdGl2aXR5Lml0.1679993952978");

        if (accessToken.isPresent()) {
            ResponseEntity response = accessTokenService.checkAccessToken(accessToken.get());

            assertEquals(IM_USED_RESPONSE.getStatusCode(), response.getStatusCode());
        }

    }

    @Test
    void checkAccessTokenAfter10MinNegativeTest() {

        Optional<AccessToken> accessToken = accessTokenRepository.findByValue("NsPdhfQDZAPR.Y2ljY2lvLmdyYXppYW5pQHByb2FjdGl2aXR5Lml0.1679993952978");

        if (accessToken.isPresent()) {
            ResponseEntity response = accessTokenService.checkAccessToken(accessToken.get());

            assertEquals(GONE_RESPONSE.getStatusCode(), response.getStatusCode());
        }

    }
}
