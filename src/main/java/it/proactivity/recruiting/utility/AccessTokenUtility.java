package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.dto.LoginDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AccessTokenUtility {

    public String createStringAccessToken(String username) {

        String random = RandomStringUtils.randomAlphabetic(12);
        String encoded = encodeString(username);
        Long instant = Instant.now().toEpochMilli();

       return random + "." + encoded + "." + instant;
    }

    private static String encodeString(String stringToDecrypt) {
        return new String(Base64.encodeBase64(stringToDecrypt.getBytes()));
    }
}
