package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobPositionDtoBuilder;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.model.dto.JobPositionWithSkillsDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobPositionUtility {

    @Autowired
    GlobalValidator globalValidator;

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
        if (!globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getTitle()) ||
                jobPositionWithSkillsDto.getTitle().length() > 50 ||
                !globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getArea()) ||
                jobPositionWithSkillsDto.getArea().length() > 20 ||
                !globalValidator.validateDescription(jobPositionWithSkillsDto.getDescription()) ||
                !globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getCity()) ||
                jobPositionWithSkillsDto.getCity().length() > 50 ||
                !globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getRegion()) ||
                jobPositionWithSkillsDto.getRegion().length() > 30 ||
                !globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getCountry()) ||
                jobPositionWithSkillsDto.getCountry().length() > 30 || !jobPositionWithSkillsDto.getIsActive() ||
                !globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getCompanyName()) ||
                jobPositionWithSkillsDto.getSkillLevelDtos().size() < 2) {
            return false;
        }
        return true;
    }
}
