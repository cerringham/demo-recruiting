package it.proactivity.recruiting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewAndUrgentJobPositionDto {

    private String status;

    private String companyName;

    private String area;

    private String title;

    private String description;
}
