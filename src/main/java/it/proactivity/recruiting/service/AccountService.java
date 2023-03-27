package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.dto.AccountDto;
import it.proactivity.recruiting.utility.AccountUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountUtility accountUtility;
    public ResponseEntity addAccount(AccountDto accountDto) {
        if (!accountUtility.validateAccountDto(accountDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
