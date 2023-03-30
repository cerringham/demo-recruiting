package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AccessTokenUtility {

    @Autowired
    AccessTokenRepository accessTokenRepository;


    public Boolean isExpired(AccessToken accessToken) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = accessToken.getCreationTokenDateTime().plusMinutes(10);
        return now.isAfter(expirationTime);
    }

    public Boolean verifyTokenForAdminAndHr(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        Optional<String> roleNameForValidToken = accessTokenRepository.findRoleNameByTokenValueForAdminAndHr(token);
        return roleNameForValidToken.isPresent();
    }
}
