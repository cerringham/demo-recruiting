package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobInterviewTypeDtoBuilder;
import it.proactivity.recruiting.model.dto.JobInterviewTypeDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class JobInterviewTypeUtility {

    @Autowired
    AccessTokenUtility accessTokenUtility;

    private static final List<String> AUTHORIZED_ROLE = List.of("hr", "admin");

    public JobInterviewTypeDto createJobInterviewTypeDto(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("The name can't be null or empty for the creation of JobInterviewTypeDto");
        }

        return JobInterviewTypeDtoBuilder.newBuilder(name).build();
    }

    public Boolean authorizeJobInterviewTypeService(String accessToken) {
        Set<Predicate<String>> predicateSet = accessTokenUtility.createPredicateSet(AUTHORIZED_ROLE);
        return accessTokenUtility.verifyAccountCredential(accessToken, predicateSet);
    }
}
