package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.AccessTokenBuilder;
import it.proactivity.recruiting.builder.AccountBuilder;
import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AccountDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.AccountRepository;
import it.proactivity.recruiting.utility.AccessTokenUtility;
import it.proactivity.recruiting.utility.AccountUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountUtility accountUtility;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccessTokenUtility accessTokenUtility;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    public ResponseEntity addAccount(AccountDto accountDto){
        if (!accountUtility.validateAccountDto(accountDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        String correctPassword = accountUtility.correctPassword(accountDto.getPassword());
        if (correctPassword == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Account account = AccountBuilder.newBuilder(accountDto.getName())
                .surname(accountDto.getSurname())
                .email(accountDto.getEmail())
                .username(accountDto.getUsername())
                .password(correctPassword)
                .isActive(true)
                .build();
        accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity login(LoginDto loginDto) {
        if (!accountUtility.validateAccountForLogin(loginDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        String correctPassword = accountUtility.correctPassword(loginDto.getPassword());
        Optional<Account> account = accountRepository.findByUsernameAndPassword(loginDto.getUsername(), correctPassword);
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        AccessToken accessToken = accessTokenUtility.createAccessToken(loginDto.getUsername());
        accessTokenUtility.setAccessTokenToFalse();
        accessTokenRepository.save(accessToken);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}