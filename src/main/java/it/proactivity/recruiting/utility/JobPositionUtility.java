package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobPositionDtoBuilder;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.model.dto.JobPositionWithSkillsDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobPositionUtility {

    @Autowired
    GlobalValidator globalValidator;

    @Value("${recruiting.maxLength50}")
    private int maxLength50;

    @Value("${recruiting.maxLength30}")
    private int maxLength30;

    @Value("${recruiting.maxLength20}")
    private int maxLength20;

    @Value("${recruiting.minSkills}")
    private int minSkills;

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
                jobPositionWithSkillsDto.getTitle().length() > maxLength50 ||
                !globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getArea()) ||
                jobPositionWithSkillsDto.getArea().length() > maxLength20 ||
                !globalValidator.validateDescription(jobPositionWithSkillsDto.getDescription()) ||
                !globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getCity()) ||
                jobPositionWithSkillsDto.getCity().length() > maxLength50 ||
                !globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getRegion()) ||
                jobPositionWithSkillsDto.getRegion().length() > maxLength30 ||
                !globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getCountry()) ||
                jobPositionWithSkillsDto.getCountry().length() > maxLength30 ||
                        !jobPositionWithSkillsDto.getIsActive() ||
                !globalValidator.validateStringAlphaSpace(jobPositionWithSkillsDto.getCompanyName()) ||
                jobPositionWithSkillsDto.getSkillLevelDtos().size() < minSkills)
        {
            return false;
        }
        return true;
    }
}
