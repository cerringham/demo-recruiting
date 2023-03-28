package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.AccessTokenBuilder;
import it.proactivity.recruiting.builder.AccountBuilder;
import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AccountDto;
import it.proactivity.recruiting.repository.AccountRepository;
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
    public ResponseEntity addAccount(AccountDto accountDto){
        if (!accountUtility.validateAccountDto(accountDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        String correctPassword = null;
        try {
            correctPassword = hashPassword(accountDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
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

    private static String hashPassword(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(plainText.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return number.toString(16);
    }

    public ResponseEntity login(AccountDto accountDto) {
        if (!accountUtility.validateAccountForLogin(accountDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Account> account = accountRepository.findByUsernameAndPassword(accountDto.getUsername(),
                accountDto.getPassword());
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        AccessToken accessToken = AccessTokenBuilder.newBuilder("Name").duration()
    }
}