package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.CompanyRole;

public class CompanyRoleBuilder {

    private String name;

    private Boolean isActive;

    private CompanyRoleBuilder(String name) {
        this.name = name;
    }

    public static CompanyRoleBuilder newBuilder(String name) {
        return new CompanyRoleBuilder(name);
    }

    public CompanyRoleBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public CompanyRole build() {
        return new CompanyRole(name, isActive);
    }
}
