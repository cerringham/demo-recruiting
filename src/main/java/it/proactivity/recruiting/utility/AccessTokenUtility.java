package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.AccessToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccessTokenUtility {

    public Boolean isExpired(AccessToken accessToken) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = accessToken.getCreationTokenDateTime().plusMinutes(10);
        return now.isAfter(expirationTime);
    }
}
