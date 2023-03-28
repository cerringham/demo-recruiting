package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessTokenController {

    @Autowired
    AccessTokenService accessTokenService;

    @GetMapping("/check-access-token")
    public ResponseEntity checkAccessToken(AccessToken token) {
        return accessTokenService.checkAccessToken(token);
    }


}
