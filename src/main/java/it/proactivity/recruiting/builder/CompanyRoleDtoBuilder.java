package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.CompanyRoleDto;

public class CompanyRoleDtoBuilder {

    private String name;

    private Boolean isActive;

    private CompanyRoleDtoBuilder (String name) {
        this.name = name;
    }

    public static CompanyRoleDtoBuilder newBuilder(String name) {

        return new CompanyRoleDtoBuilder(name);
    }

    public CompanyRoleDtoBuilder isActive (Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public CompanyRoleDto build() {
        return new CompanyRoleDto(name, isActive);
    }
}
