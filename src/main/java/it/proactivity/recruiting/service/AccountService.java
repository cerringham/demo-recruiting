package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AddAccountDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.AccountRepository;
import it.proactivity.recruiting.utility.AccountUtility;
import it.proactivity.recruiting.utility.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountValidator accountValidator;

    @Autowired
    AccountUtility accountUtility;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    public ResponseEntity addAccount(AddAccountDto dto) {
        if (!accountValidator.validateAddAccountDto(dto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Account account = accountUtility.createAccount(dto);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity login(LoginDto dto) {
        if (!accountValidator.validateLoginDto(dto)) {
            // eliminare controlli superflui
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // antipattern, creare un metodo che restituisce hashPassword
        String hashPassword;
        try {
            hashPassword = accountUtility.hashPassword(dto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<Account> account = accountRepository.findByUsernameAndPassword(dto.getUsername(), hashPassword);
        if (account.isEmpty() || !account.get().getIsActive()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AccessToken token = accountUtility.createAccessToken(dto.getUsername());
        token.setAccount(account.get());
        accountUtility.setLastAccessTokenToFalse(account.get());
        accessTokenRepository.save(token);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
