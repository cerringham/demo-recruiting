package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.repository.ApplicationConstantRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CompanyRoleValidator {

    @Autowired
    ApplicationConstantRepository applicationConstantRepository;

    public Boolean validateName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }

        int nameMaxLength = Integer.parseInt(applicationConstantRepository.companyRoleNameMaxLength());

        return StringUtils.isAlphaSpace(name) && name.length() < nameMaxLength;
    }
}
