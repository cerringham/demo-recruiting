package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.Account;

public class AccountBuilder {

    private String name;

    private String surname;

    private String email;

    private String username;

    private String password;

    private Boolean isActive;

    private AccountBuilder(String name) {
        this.name = name;
    }

    public static AccountBuilder newBuilder(String name) {
        return new AccountBuilder(name);
    }

    public AccountBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public AccountBuilder email(String email) {
        this.email = email;
        return this;
    }

    public AccountBuilder username(String username) {
        this.username = username;
        return this;
    }

    public AccountBuilder password(String password) {
        this.password = password;
        return this;
    }

    public AccountBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Account build() {
        return new Account(name, surname, email, username, password, isActive);
    }

}
