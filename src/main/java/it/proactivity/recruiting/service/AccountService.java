package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.AccountBuilder;
import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AccountDto;
import it.proactivity.recruiting.repository.AccountRepository;
import it.proactivity.recruiting.utility.AccountUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountUtility accountUtility;

    @Autowired
    AccountRepository accountRepository;
    public ResponseEntity addAccount(AccountDto accountDto) {
        if (!accountUtility.validateAccountDto(accountDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Account account = AccountBuilder.newBuilder(accountDto.getName())
                .surname(accountDto.getSurname())
                .email(accountDto.getEmail())
                .username(accountDto.getUsername())
                .password(accountDto.getPassword())
                .isActive(true)
                .build();
        accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}