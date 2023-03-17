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
    private Boolean isActive;

    public CompanyRoleDto(String name, Boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public CompanyRoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " is active : " + isActive;
    }
}
