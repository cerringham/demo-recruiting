package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;

import java.time.LocalTime;

public class AccessTokenBuilder {

    private String name;

    private LocalTime duration;

    private Account account;

    private Boolean isActive;

    private AccessTokenBuilder(String name) {
        this.name = name;
    }

    public static AccessTokenBuilder newBuilder(String name) {
        return new AccessTokenBuilder(name);
    }

    public AccessTokenBuilder duration(LocalTime duration) {
        this.duration = duration;
        return this;
    }

    public AccessTokenBuilder account(Account account) {
        this.account = account;
        return this;
    }

    public AccessTokenBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public AccessToken build() {
        return new AccessToken(name,duration, account, isActive);
    }
}
