package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.myEnum.Level;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class JobPositionValidator {

    @Value("${recruiting.minimumSkillsForJobPosition}")
    private int minimumSkillsForJobPosition;

    @Value("${recruiting.jobPositionTitleMaxLength}")
    private int titleMaxLength;

    @Value("${recruiting.jobPositionAreaMaxLength}")
    private int areaMaxLength;

    @Value("${recruiting.jobPositionCityMaxLength}")
    private int cityMaxLength;

    @Value("${recruiting.jobPositionRegionMaxLength}")
    private int regionMaxLength;

    @Value("${recruiting.jobPositionCountryMaxLength}")
    private int countryMaxLength;

    @Value("${recruiting.jobPositionCompanyMaxLength}")
    private int companyNameMaxLength;

    @Value("${recruiting.jobPositionStatusMaxLength}")
    private int jobPositionStatusNameMaxLength;

    public Boolean validateSkillLevelMap(Map<String, Level> skillLevelMap) {
        if (skillLevelMap == null) {
            return false;
        }
        Set<String> skillNames = skillLevelMap.keySet();
        List<Level> levels = new ArrayList<>(skillLevelMap.values());

        if (skillNames.size() < minimumSkillsForJobPosition) {
            return false;
        }

        if (levels.contains(null)) {
            return false;
        }
        return true;
    }

    public Boolean validateTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return false;
        }
        return StringUtils.isAlphaSpace(title) && title.length() <= titleMaxLength;
    }

    public Boolean validateArea(String area) {
        if (StringUtils.isEmpty(area)) {
            return false;
        }
        return StringUtils.isAlphaSpace(area) && area.length() <= areaMaxLength;
    }

    public Boolean validateCity(String city) {
        if (StringUtils.isEmpty(city)) {
            return false;
        }
        return StringUtils.isAlphaSpace(city) && city.length() <= cityMaxLength;
    }

    public Boolean validateRegion(String region) {
        if (StringUtils.isEmpty(region)) {
            return false;
        }
        return StringUtils.isAlphaSpace(region) && region.length() <= regionMaxLength;
    }

    public Boolean validateCountry(String country) {
        if (StringUtils.isEmpty(country)) {
            return false;
        }
        return StringUtils.isAlphaSpace(country) && country.length() <= countryMaxLength;
    }

    public Boolean validateCompanyName(String companyName) {
        if (StringUtils.isEmpty(companyName)) {
            return false;
        }

        return StringUtils.isAlpha(companyName) && companyName.length() <= companyNameMaxLength;
    }

    public Boolean validateJobPositionStatusName(String jobPositionStatusName) {
        if (StringUtils.isEmpty(jobPositionStatusName)) {
            return false;
        }
        return StringUtils.isAlphaSpace(jobPositionStatusName) &&
                jobPositionStatusName.length() <= jobPositionStatusNameMaxLength;
    }
}
