package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AddAccountDto;
import it.proactivity.recruiting.repository.AccountRepository;
import it.proactivity.recruiting.utility.AccountUtility;
import it.proactivity.recruiting.utility.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountValidator accountValidator;

    @Autowired
    AccountUtility accountUtility;

    @Autowired
    AccountRepository accountRepository;

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
}
