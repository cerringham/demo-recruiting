package it.proactivity.recruiting.methods;

import it.proactivity.recruiting.model.dto.AccountInformationDto;
import it.proactivity.recruiting.repository.AccountRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountMethods {

    @Autowired
    AccountRepository accountRepository;

    public Optional<AccountInformationDto> retrieveAccountInformation(String username) {
        if (StringUtils.isEmpty(username))
            return Optional.empty();

        return accountRepository.findByUsernameAndIsActiveTrue(username)
                .map(account -> new AccountInformationDto(
                        account.getId(),
                        account.getName(),
                        account.getSurname(),
                        account.getUsername(),
                        account.getEmail(),
                        account.getRole().getId(),
                        account.getRole().getName()
                ));
    }
}
