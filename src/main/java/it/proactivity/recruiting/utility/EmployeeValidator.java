package it.proactivity.recruiting.utility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator {

    public Boolean validateCompanyRole(String companyRole) {
        if (StringUtils.isEmpty(companyRole)) {
            return false;
        }
        return StringUtils.isAlphaSpace(companyRole);
    }
}

