package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.dto.AccessTokenDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.utility.AccessTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class AccessTokenService {

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    AccessTokenUtility accessTokenUtility;

    public ResponseEntity checkAccessToken(AccessTokenDto accessTokenDto) {
        if (!accessTokenUtility.checkIfAccessTokenNameIsValid(accessTokenDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<AccessToken> accessToken = accessTokenRepository.findById(accessTokenDto.getId());
        if (accessToken.isPresent()) {
            if (accessToken.get().getExpiration().isBefore(LocalTime.now())) {
                accessToken.get().setIsActive(false);
                accessTokenRepository.save(accessToken.get());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
