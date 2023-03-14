package it.proactivity.recruiting.model.dto;

import it.proactivity.recruiting.myEnum.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class JobPositionInsertionDto extends JobPositionDto {

    private Long id;
    private String jobPositionStatusName;
    private String companyName;
    private Map<String, Level> skillLevelMap = new HashMap<>();

    public JobPositionInsertionDto(String title, String area, String description, String city, String region, String country,
                                   Boolean isActive, String companyName, Map<String, Level> skillLevelMap) {

        super(title, area, description, city, region, country, isActive);
        this.companyName = companyName;
        this.skillLevelMap = skillLevelMap;
    }

}
