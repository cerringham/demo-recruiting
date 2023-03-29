package it.proactivity.recruiting.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountInformationDto {

    private Long accountId;

    private String accountName;

    private String accountSurname;

    private String accountUsername;

    private String accountEmail;

    private Long roleId;

    private String roleName;

    public AccountInformationDto(Long accountId, String accountName, String accountSurname, String accountUsername,
                                 String accountEmail, Long roleId, String roleName) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountSurname = accountSurname;
        this.accountUsername = accountUsername;
        this.accountEmail = accountEmail;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return """
                Name : %s,
                Surname : %s,
                Username : %s,
                Email : %s,
                Role Name : %s
                """.formatted(accountName, accountSurname, accountUsername, accountEmail, roleName);
    }
}
