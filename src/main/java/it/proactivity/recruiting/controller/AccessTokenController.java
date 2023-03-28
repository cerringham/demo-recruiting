package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessTokenController {

    @Autowired
    AccessTokenService accessTokenService;
/*
    @GetMapping("/check-access-token")
    public ResponseEntity checkAccessToken(@RequestParam String tokenValue) {
        return accessTokenService.checkAccessToken(tokenValue);
    }

 */
}
