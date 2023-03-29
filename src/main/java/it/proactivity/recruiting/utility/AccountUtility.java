package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.AccessTokenBuilder;
import it.proactivity.recruiting.builder.AccountBuilder;
import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AccountInformationDto;
import it.proactivity.recruiting.model.dto.AddAccountDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.AccountRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AccountUtility {

    private static final Character SEPARATOR = '.';

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(AddAccountDto dto) {

        try {
            String hashedPassword = hashPassword(dto.getPassword());
            return AccountBuilder.newBuilder(dto.getName())
                    .surname(dto.getSurname())
                    .email(dto.getEmail())
                    .username(dto.getUsername())
                    .password(hashedPassword)
                    .isActive(true)
                    .build();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public String createHashPassword(String password) {
        try {
            return hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public String hashPassword(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(plainText.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return number.toString(16);
    }

    public AccessToken createAccessToken(String username) {

        LocalDateTime tokenCreationDateTime = LocalDateTime.now();
        String tokenValue = createValueForToken(username);

        return AccessTokenBuilder.newBuilder(tokenValue)
                .creationTokenDateTime(tokenCreationDateTime)
                .isActive(true)
                .build();
    }

    public void setLastAccessTokenToFalse(Account account) {
        Optional<AccessToken> lastAccessToken = accessTokenRepository.findFirstByAccountOrderByIdDesc(account);
        if (lastAccessToken.isPresent()) {
            lastAccessToken.get().setIsActive(false);
            accessTokenRepository.save(lastAccessToken.get());
        }
    }

    public String encodeString(String stringToDecrypt) {
        return new String(Base64.encodeBase64(stringToDecrypt.getBytes()));
    }

    public Optional<AccountInformationDto> getAccountInformation(String username) {
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

    private String createValueForToken(String username) {
        String randomString = RandomStringUtils.random(12, true, false);
        StringBuilder sb = new StringBuilder(randomString);
        sb.append(SEPARATOR);

        String encodedUsername = encodeString(username);
        sb.append(encodedUsername);
        sb.append(SEPARATOR);

        Instant instant = Instant.now();
        sb.append(instant.toEpochMilli());
        return sb.toString();
    }
}
