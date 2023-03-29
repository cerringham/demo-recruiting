package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.Company;

public class CompanyBuilder {

    private final String name;

    private Long cooId;

    private Boolean isActive;

    private CompanyBuilder(String name) {
        this.name = name;
    }

    public static CompanyBuilder newBuilder(String name) {
        return new CompanyBuilder(name);
    }

    public CompanyBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Company build() {
        return new Company(name, cooId, isActive);
    }
}
