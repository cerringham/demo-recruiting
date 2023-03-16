package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.project_enum.Level;
import it.proactivity.recruiting.repository.ApplicationConstantRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class JobPositionValidator {

    @Autowired
    ApplicationConstantRepository applicationConstantRepository;

    public Boolean validateSkillLevelMap(Map<String, Level> skillLevelMap) {
        if (skillLevelMap == null) {
            return false;
        }
        Set<String> skillNames = skillLevelMap.keySet();
        List<Level> levels = new ArrayList<>(skillLevelMap.values());

        if (skillNames.size() < Integer.parseInt(applicationConstantRepository.jobPositionMinimumSkills())) {
            return false;
        }

        return !levels.contains(null);
    }

    public Boolean validateTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return false;
        }
        return StringUtils.isAlphaSpace(title) && title.length() <= Integer.parseInt(applicationConstantRepository.jobPositionTitleMaxLength());
    }

    public Boolean validateArea(String area) {
        if (StringUtils.isEmpty(area)) {
            return false;
        }
        return StringUtils.isAlphaSpace(area) && area.length() <= Integer.parseInt(applicationConstantRepository.jobPositionAreaMaxLength());
    }

    public Boolean validateCity(String city) {
        if (StringUtils.isEmpty(city)) {
            return false;
        }
        return StringUtils.isAlphaSpace(city) && city.length() <= Integer.parseInt(applicationConstantRepository.jobPositionCityMaxLength());
    }

    public Boolean validateRegion(String region) {
        if (StringUtils.isEmpty(region)) {
            return false;
        }
        return StringUtils.isAlphaSpace(region) && region.length() <= Integer.parseInt(applicationConstantRepository.jobPositionRegionMaxLength());
    }

    public Boolean validateCountry(String country) {
        if (StringUtils.isEmpty(country)) {
            return false;
        }
        return StringUtils.isAlphaSpace(country) && country.length() <= Integer.parseInt(applicationConstantRepository.jobPositionCountryMaxLength());
    }

    public Boolean validateCompanyName(String companyName) {
        if (StringUtils.isEmpty(companyName)) {
            return false;
        }

        return StringUtils.isAlpha(companyName) && companyName.length() <= Integer.parseInt(applicationConstantRepository.companyNameMaxLength());
    }

    public Boolean validateJobPositionStatusName(String jobPositionStatusName) {
        if (StringUtils.isEmpty(jobPositionStatusName)) {
            return false;
        }
        return StringUtils.isAlphaSpace(jobPositionStatusName) &&
                jobPositionStatusName.length() <= Integer.parseInt(applicationConstantRepository.jobPositionStatusNameMaxLength());
    }
}
