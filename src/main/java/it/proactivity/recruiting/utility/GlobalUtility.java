package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;

@Component
public class GlobalUtility {

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    AccountRepository accountRepository;


    public Optional<AccessToken> getTokenIfItsValid(String tokenName) {
        Optional<AccessToken> accessToken = accessTokenRepository.findByNameAndIsActive(tokenName, true);
        if (accessToken.isPresent() && accessToken.get().getExpiration().isAfter(LocalTime.now())) {
            return accessToken;
        }else {
            return Optional.empty();
        }
    }

    public Optional<Account> getAccountFromToken(AccessToken accessToken) {
        Optional<Account> account = accountRepository.findById(accessToken.getAccount().getId());
        return account;
    }

    public Boolean checkIfRoleIsAuthorized(Account account, Set<String> authorizedRoles) {
        if (account != null && !authorizedRoles.isEmpty()) {
            return authorizedRoles.contains(account.getRole().getName());
        }else {
            return false;
        }
    }

    public Boolean checkIfTokenAndAccountAreValid(String tokenName, Set<String> roleNames) {
        Optional<AccessToken> accessToken = getTokenIfItsValid(tokenName);
        Optional<Account> account = getAccountFromToken(accessToken.get());
        if (accessToken.isPresent() && account.isPresent() && checkIfRoleIsAuthorized(account.get(), roleNames)) {
            return true;
        }else {
            return false;
        }
    }
}
