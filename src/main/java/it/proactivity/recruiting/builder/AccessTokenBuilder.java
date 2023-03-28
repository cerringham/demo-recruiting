package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;

import java.time.LocalTime;

public class AccessTokenBuilder {

    private LocalTime duration;

    private Account account;

    private Boolean isActive;

    private String name;

    private AccessTokenBuilder(Account account) {
        this.account = account;
    }

    public static AccessTokenBuilder newBuilder(Account account) {
        return new AccessTokenBuilder(account);
    }

    public AccessTokenBuilder duration(LocalTime duration) {
        this.duration = duration;
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

    public AccessToken build() {
        return new AccessToken(duration, account, isActive, name);
    }
}
