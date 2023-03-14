package it.proactivity.recruiting.utility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class CompanyRoleValidator {

    @Value("${recruiting.companyRole.nameMaxLength}")
    private int nameMaxLength;


    public Boolean validateName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return StringUtils.isAlphaSpace(name) && name.length() < nameMaxLength;
    }
}
