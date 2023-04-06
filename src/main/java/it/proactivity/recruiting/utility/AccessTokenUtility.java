package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.AccessTokenBuilder;
import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.dto.AccessTokenDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Component
public class AccessTokenUtility {

    @Autowired
    AccountUtility accountUtility;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Value("${recruiting.authorizedRoles}")
    private List<String> authorizedRoles;

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

    // TODO la query deve gestire il flag isPresent
    // occorre controllare che il token sia ancora valido ovvero non scaduto
    public Boolean checkIfTokenIsActive(String tokenName) {
        Optional<AccessToken> accessToken = accessTokenRepository.findByName(tokenName);
        if (accessToken.isPresent()) {
            if (accessToken.get().getIsActive()) {
                return true;
            }
            return false;
        }
        return false;
    }

    // TODO Input token
    // a - recupero l'account dal token
    // b -verifico il ruolo associato all'account
    // c - ruolo == HR || ruolo == admin
    // la funzione deve essere il più generica possibile (esempio in input andiamo a prendere  una collection di ruoli)
    // checkAccountPresence() --> fa solo a
    // checkRoleAccount(account, set<Ruoli>) --> fa b e c
    public Boolean checkIfTokenBelongsToRequiredAccount(String tokenName) {
        Optional<AccessToken> accessToken = accessTokenRepository.findByName(tokenName);
        if (authorizedRoles.contains(accessToken.get().getAccount().getRole().getName())) {
            return true;
        }
        return false;
    }
}
