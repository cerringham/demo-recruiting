package it.proactivity.recruiting.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountWithRoleDto {

    private Long accountId;
    private String accountName;
    private String accountSurname;
    private String accountUsername;
    private String accountEmail;
    private Long roleId;
    private String roleName;

    @Override
    public String toString() {
        return "AccountWithRoleDto{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", accountSurname='" + accountSurname + '\'' +
                ", accountUsername='" + accountUsername + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
