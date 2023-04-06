package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.AccessTokenBuilder;
import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AccessTokenDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.AccountRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class AccessTokenUtility {

    @Autowired
    AccountUtility accountUtility;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    AccountRepository accountRepository;


    private static String TOKEN_SEPARATOR = ".";

    public AccessToken createAccessToken(String username) {

        String random = RandomStringUtils.randomAlphabetic(12);
        String encoded = encodeString(username);
        Long instant = Instant.now().toEpochMilli();
        LocalTime duration = Instant.ofEpochMilli(instant + 600000L).atZone(ZoneId.systemDefault()).toLocalTime();

        String name = random + TOKEN_SEPARATOR + encoded + TOKEN_SEPARATOR + instant;

        return AccessTokenBuilder.newBuilder(accountUtility.getAccountFromUsername(username))
                .name(name)
                .isActive(true)
                .expiration(duration)
                .build();
    }

    private static String encodeString(String stringToDecrypt) {
        return new String(Base64.encodeBase64(stringToDecrypt.getBytes()));
    }

    public void setAccessTokenToFalse() {
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        accessTokenList.stream().forEach(a -> a.setIsActive(false));
        accessTokenRepository.saveAll(accessTokenList);
    }

    public Boolean checkIfAccessTokenNameIsValid(AccessTokenDto accessTokenDto) {
        String[] name = accessTokenDto.getName().split("\\.");
        if (StringUtils.isAlpha(name[0]) && encodeString(accessTokenDto.getAccountDto().getUsername()).equals(name[1])
                && StringUtils.isNumeric(name[2])) {
            return true;
        }
        return false;
    }

    public Boolean checkIfTokenIsActive(String tokenName) {
        Optional<AccessToken> accessToken = accessTokenRepository.findByNameAndIsActive(tokenName, true);
        if (accessToken.isPresent() && accessToken.get().getExpiration().isAfter(LocalTime.now())) {
            return true;
        }
        return false;
    }

    public Account getAccountFromToken(String tokenName) {
        Optional<AccessToken> accessToken = accessTokenRepository.findByName(tokenName);
        if (accessToken.isEmpty()) {
            return null;
        }
        Optional<Account> account = accountRepository.findById(accessToken.get().getAccount().getId());
        if (account.isEmpty()) {
            return null;
        }
        return account.get();
    }

    public Boolean checkIfRoleIsAuthorized(Account account, Set<String> authorizedRoles) {
        if (account != null && !authorizedRoles.isEmpty()) {
            if (authorizedRoles.contains(account.getRole().getName())) {
                return true;
            }
            return false;
        }
        return false;
    }
}
