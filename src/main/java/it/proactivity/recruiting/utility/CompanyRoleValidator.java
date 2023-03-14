package it.proactivity.recruiting.utility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class CompanyRoleValidator {

    private final int NAME_MAX_LENGTH = 30;


    public Boolean validateName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return StringUtils.isAlphaSpace(name) && name.length() < NAME_MAX_LENGTH;
    }
}
