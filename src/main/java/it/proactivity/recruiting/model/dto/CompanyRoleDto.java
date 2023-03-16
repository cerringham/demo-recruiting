package it.proactivity.recruiting.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CompanyRoleDto {
    private Long id;
    private String name;
    private String newName;

    private Boolean isActive;

    public CompanyRoleDto(String name, Boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public CompanyRoleDto(String name, String newName, Boolean isActive) {
        this.name = name;
        this.newName = newName;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return name + " is active : " + isActive;
    }
}
