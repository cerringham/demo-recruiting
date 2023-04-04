package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;

import java.time.LocalTime;

public class AccessTokenBuilder {

    private LocalTime expiration;

    private Account account;

    private Boolean isActive;

    private String name;

    private AccessTokenBuilder(Account account) {
        this.account = account;
    }

    public static AccessTokenBuilder newBuilder(Account account) {
        return new AccessTokenBuilder(account);
    }

    public AccessTokenBuilder expiration(LocalTime expiration) {
        this.expiration = expiration;
        return this;
    }

    public AccessTokenBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public AccessTokenBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AccessTokenBuilder account(Account account) {
        this.account = account;
        return this;
    }
    public AccessToken build() {
        return new AccessToken(expiration, account, isActive, name);
    }
}
