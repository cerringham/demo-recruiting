package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;


import java.time.LocalDateTime;


public class AccessTokenBuilder {

    private final String value;

    private LocalDateTime creationTokenDateTime;

    private Boolean isActive;

    private Account account;

    private AccessTokenBuilder(String value) {
        this.value = value;
    }

    public static AccessTokenBuilder newBuilder(String value) {
        return new AccessTokenBuilder(value);
    }

    public AccessTokenBuilder creationTokenDateTime(LocalDateTime creationTokenDateTime) {
        this.creationTokenDateTime = creationTokenDateTime;
        return this;
    }


    public AccessTokenBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public AccessTokenBuilder account(Account account) {
        this.account = account;
        return this;
    }

    public AccessToken build() {
        return new AccessToken(value, creationTokenDateTime, isActive, account);
    }
}
