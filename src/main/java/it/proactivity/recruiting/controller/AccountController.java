package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.AddAccountDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.repository.AccountRepository;
import it.proactivity.recruiting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("add-account")
    public ResponseEntity addAccount(@RequestBody AddAccountDto dto, @RequestHeader("Token") String accessToken) {
        return accountService.addAccount(dto, accessToken);
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto dto) {
        return accountService.login(dto);
    }
}
