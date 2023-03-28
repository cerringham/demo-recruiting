package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.AccountInformationDto;

public class AccountInformationDtoBuilder {

    private Long accountId;

    private String accountName;

    private String accountSurname;

    private String accountUsername;

    private String accountEmail;

    private Long roleId;

    private String roleName;

    private AccountInformationDtoBuilder(Long accountId) {
        this.accountId = accountId;
    }

    public static AccountInformationDtoBuilder newBuilder(Long accountId) {
        return new AccountInformationDtoBuilder(accountId);
    }

    public AccountInformationDtoBuilder accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public AccountInformationDtoBuilder accountSurname(String accountSurname) {
        this.accountSurname = accountSurname;
        return this;
    }

    public AccountInformationDtoBuilder accountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
        return this;
    }

    public AccountInformationDtoBuilder accountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
        return this;
    }

    public AccountInformationDtoBuilder roleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public AccountInformationDtoBuilder roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public AccountInformationDto build() {
        return new AccountInformationDto(accountId, accountName, accountSurname, accountUsername, accountEmail, roleId,
                roleName);
    }
}
