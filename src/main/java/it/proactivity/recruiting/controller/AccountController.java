package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.AccountDto;
import it.proactivity.recruiting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/add-account")
    public ResponseEntity addAccount(@RequestBody AccountDto accountDto) {
        return accountService.addAccount(accountDto);
    }


}