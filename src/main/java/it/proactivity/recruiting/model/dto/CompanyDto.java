package it.proactivity.recruiting.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {

    private String name;

    private Boolean isActive;

    public CompanyDto(String name, Boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "CompanyDto{" +
                "name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
