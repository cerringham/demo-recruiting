package it.proactivity.recruiting.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SimpleJobPositionDto {
    private String status;
    private String companyName;
    private String area;
    private String title;
    private String description;

    public SimpleJobPositionDto(String status, String companyName, String area, String title, String description) {
        this.status = status;
        this.companyName = companyName;
        this.area = area;
        this.title = title;
        this.description = description;
    }
}
