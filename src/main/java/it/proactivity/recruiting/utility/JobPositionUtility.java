package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobPositionBuilder;
import it.proactivity.recruiting.builder.JobPositionDtoBuilder;
import it.proactivity.recruiting.builder.SkillBuilder;
import it.proactivity.recruiting.builder.SkillLevelBuilder;
import it.proactivity.recruiting.model.*;
import it.proactivity.recruiting.model.dto.JobPositionInsertionDto;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.repository.JobPositionStatusRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JobPositionUtility {

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobPositionStatusRepository jobPositionStatusRepository;
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

    public Boolean validateAllStringParametersForJobPositionInsertionDto(JobPositionInsertionDto dto) {

        return globalValidator.validateStringAlphaSpace(dto.getTitle()) &&
                globalValidator.validateStringAlphaSpace(dto.getArea()) &&
                globalValidator.validateStringAlphaNumericSpace(dto.getDescription()) &&
                globalValidator.validateStringAlphaSpace(dto.getCity()) &&
                globalValidator.validateStringAlphaSpace(dto.getRegion()) &&
                globalValidator.validateStringAlphaSpace(dto.getCountry()) &&
                globalValidator.validateStringAlphaSpace(dto.getCompanyName());

    }

    public JobPosition createJobPosition(JobPositionInsertionDto dto) {

        Optional<Company> company = companyRepository
                .findByNameIgnoreCaseAndIsActive(WordUtils.capitalizeFully(dto.getCompanyName()), true);

        Optional<JobPositionStatus> jobPositionStatus = jobPositionStatusRepository
                .findByNameIgnoreCaseAndIsActive("new",true);

        if (company.isEmpty()) {
            throw new IllegalArgumentException("Company not found");
        }

        if (jobPositionStatus.isEmpty()) {
            throw new IllegalArgumentException("JobPositionStatus not found");
        }

        return JobPositionBuilder.newBuilder(dto.getTitle())
                .area(dto.getArea())
                .description(dto.getDescription())
                .city(dto.getCity())
                .region(dto.getRegion())
                .country(dto.getCountry())
                .company(company.get())
                .jobPositionStatus(jobPositionStatus.get())
                .isActive(true)
                .build();
    }

    public List<SkillLevel> createSkillList(Map<Skill, Level> skillLevelMap, JobPosition jobPosition) {

        List<SkillLevel> skillLevelList = new ArrayList<>();

        skillLevelMap.entrySet().stream()
                .forEach(e -> {
                    SkillLevel skillLevel = SkillLevelBuilder.newBuilder(e.getKey())
                            .level(e.getValue())
                            .jobPosition(jobPosition)
                            .isActive(true)
                            .build();
                    skillLevelList.add(skillLevel);
                });
        return skillLevelList;
    }
}
