package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.AccountBuilder;
import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AddAccountDto;
import org.springframework.stereotype.Component;

@Component
public class AccountUtility {

    public Account createAccount(AddAccountDto dto) {
        return AccountBuilder.newBuilder(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .isActive(true)
                .build();
    }
}
