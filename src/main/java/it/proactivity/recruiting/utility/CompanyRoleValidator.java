package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.repository.ApplicationConstantRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CompanyRoleValidator {

    @Autowired
    ApplicationConstantRepository attributeLengthLimitRepository;

    public Boolean validateName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }

        int nameMaxLength = Integer.parseInt(attributeLengthLimitRepository.companyRoleNameMaxLength());

        return StringUtils.isAlphaSpace(name) && name.length() < nameMaxLength;
    }
}
