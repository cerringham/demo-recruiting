package it.proactivity.recruiting.methods;

import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AccountInformationDto;
import it.proactivity.recruiting.repository.AccountRepository;
import it.proactivity.recruiting.utility.AccountUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountMethods {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountUtility accountUtility;

    public Optional<AccountInformationDto> retrieveAccountInformation(String username) {
        if (StringUtils.isEmpty(username)) {
            return Optional.empty();
        }
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isEmpty() || !account.get().getIsActive()) {
            return Optional.empty();
        }
        AccountInformationDto dto = accountUtility.createAccountInformationDto(account.get());
        return Optional.of(dto);
    }
}
