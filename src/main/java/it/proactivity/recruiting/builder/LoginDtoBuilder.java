package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.LoginDto;

public class LoginDtoBuilder {

    private String username;

    private String password;

    private LoginDtoBuilder(String username) {
        this.username = username;
    }

    public static LoginDtoBuilder newBuilder(String username) {
        return new LoginDtoBuilder(username);
    }

    public LoginDtoBuilder password(String password) {
        this.password = password;
        return this;
    }

    public LoginDto build() {
        return new LoginDto(username, password);
    }
}
