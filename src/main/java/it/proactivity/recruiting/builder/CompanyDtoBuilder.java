package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.CompanyDto;
public class CompanyDtoBuilder {

    private String name;

    private Boolean isActive;

    private CompanyDtoBuilder (String name) {
        this.name = name;
    }
    public static CompanyDtoBuilder newBuilder(String name) {
        return new CompanyDtoBuilder(name);
    }

    public CompanyDtoBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public CompanyDto build() {
        return new CompanyDto(name, isActive);
    }
}
