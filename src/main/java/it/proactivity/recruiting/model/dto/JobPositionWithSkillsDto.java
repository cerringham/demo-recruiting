package it.proactivity.recruiting.model.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class JobPositionWithSkillsDto {
    private String title;

    private String area;

    private String description;

    private String city;

    private String region;

    private String country;

    private Boolean isActive;

    private String companyName;

    private List<SkillLevelDto> skillLevelDtoList;
}
