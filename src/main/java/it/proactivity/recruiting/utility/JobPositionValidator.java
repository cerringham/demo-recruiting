package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.myEnum.Level;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class JobPositionValidator {

    private static final int MINIMUM_SKILLS_FOR_JOB_POSITION = 2;

    private static final int TITLE_MAX_LENGTH = 50;

    private static final int AREA_MAX_LENGTH = 20;

    private static final int CITY_MAX_LENGTH = 50;

    private static final int REGION_MAX_LENGTH = 30;

    private static final int COUNTRY_MAX_LENGTH = 30;

    private static final int COMPANY_NAME_MAX_LENGTH = 20;

    private static final int JOB_POSITION_STATUS_NAME_MAX_LENGTH = 50;

    public Boolean validateSkillLevelMap(Map<String, Level> skillLevelMap) {
        if (skillLevelMap == null) {
            return false;
        }
        Set<String> skillNames = skillLevelMap.keySet();
        List<Level> levels = new ArrayList<>(skillLevelMap.values());

        if (skillNames.size() < MINIMUM_SKILLS_FOR_JOB_POSITION) {
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
        return StringUtils.isAlphaSpace(title) && title.length() <= TITLE_MAX_LENGTH;
    }

    public Boolean validateArea(String area) {
        if (StringUtils.isEmpty(area)) {
            return false;
        }
        return StringUtils.isAlphaSpace(area) && area.length() <= AREA_MAX_LENGTH;
    }

    public Boolean validateCity(String city) {
        if (StringUtils.isEmpty(city)) {
            return false;
        }
        return StringUtils.isAlphaSpace(city) && city.length() <= CITY_MAX_LENGTH;
    }

    public Boolean validateRegion(String region) {
        if (StringUtils.isEmpty(region)) {
            return false;
        }
        return StringUtils.isAlphaSpace(region) && region.length() <= REGION_MAX_LENGTH;
    }

    public Boolean validateCountry(String country) {
        if (StringUtils.isEmpty(country)) {
            return false;
        }
        return StringUtils.isAlphaSpace(country) && country.length() <= COUNTRY_MAX_LENGTH;
    }

    public Boolean validateCompanyName(String companyName) {
        if (StringUtils.isEmpty(companyName)) {
            return false;
        }

        return StringUtils.isAlpha(companyName) && companyName.length() <= COMPANY_NAME_MAX_LENGTH;
    }

    public Boolean validateJobPositionStatusName(String jobPositionStatusName) {
        if (StringUtils.isEmpty(jobPositionStatusName)) {
            return false;
        }
        return StringUtils.isAlphaSpace(jobPositionStatusName) &&
                jobPositionStatusName.length() <= JOB_POSITION_STATUS_NAME_MAX_LENGTH;
    }
}
