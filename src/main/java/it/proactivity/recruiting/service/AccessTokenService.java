package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.utility.AccessTokenUtility;
import it.proactivity.recruiting.utility.AccessTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService {

    @Autowired
    AccessTokenValidator accessTokenValidator;

    @Autowired
    AccessTokenUtility accessTokenUtility;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    public ResponseEntity checkAccessToken(AccessToken token) {
        if (!accessTokenValidator.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (accessTokenUtility.isExpired(token)) {
            token.setIsActive(false);
            accessTokenRepository.save(token);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
