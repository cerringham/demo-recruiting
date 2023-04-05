package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.AccountWithRoleDto;

public class AccountWithRoleDtoBuilder {

    private Long accountId;
    private String accountName;
    private String accountSurname;
    private String accountUsername;
    private String accountEmail;
    private Long roleId;
    private String roleName;

    private AccountWithRoleDtoBuilder(Long accountId) {
        this.accountId = accountId;
    }

    public static AccountWithRoleDtoBuilder newBuilder(Long accountId) {
        return new AccountWithRoleDtoBuilder(accountId);
    }

    public AccountWithRoleDtoBuilder accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public AccountWithRoleDtoBuilder accountSurname(String accountSurname) {
        this.accountSurname = accountSurname;
        return this;
    }

    public AccountWithRoleDtoBuilder accountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
        return this;
    }

    public AccountWithRoleDtoBuilder accountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
        return this;
    }

    public AccountWithRoleDtoBuilder roleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public AccountWithRoleDtoBuilder roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public AccountWithRoleDto build() {
        return new AccountWithRoleDto(accountId, accountName, accountSurname, accountUsername, accountEmail, roleId,
                roleName);
    }
}
