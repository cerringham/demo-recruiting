package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobPositionDtoBuilder;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.model.dto.JobPositionWithSkillsDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class JobPositionUtility {

    public JobPositionDto createJobPositionDto(String title, String area, String description, String city, String region,
                                               String country, Boolean isActive) {

        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(area) || StringUtils.isEmpty(description) ||
                StringUtils.isEmpty(city) || StringUtils.isEmpty(region) || StringUtils.isEmpty(country) || isActive == null) {

            throw new IllegalArgumentException("The parameters for the creation of JobPositionDto can't be null or empty");
        }

        return JobPositionDtoBuilder.newBuilder(title)
                .area(area)
                .description(description)
                .city(city)
                .region(region)
                .country(country)
                .isActive(isActive)
                .build();
    }

    public Boolean validateParametersForInsert(JobPositionWithSkillsDto jobPositionWithSkillsDto) {
        if (StringUtils.isEmpty(jobPositionWithSkillsDto.getTitle()) || StringUtils.isEmpty(jobPositionWithSkillsDto.getArea())
        || StringUtils.isEmpty(jobPositionWithSkillsDto.getDescription()) || StringUtils.isEmpty(jobPositionWithSkillsDto.getCity())
                || StringUtils.isEmpty(jobPositionWithSkillsDto.getRegion()) || StringUtils.isEmpty(jobPositionWithSkillsDto.getCountry())
                || !jobPositionWithSkillsDto.getIsActive() || StringUtils.isEmpty(jobPositionWithSkillsDto.getCompanyName()) ||
                jobPositionWithSkillsDto.getSkillLevelDtos().size() < 2) {
            return false;
        }
        return true;
    }
}
