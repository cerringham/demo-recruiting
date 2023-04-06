package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobInterviewStatusDtoBuilder;
import it.proactivity.recruiting.model.dto.JobInterviewStatusDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class JobInterviewStatusUtility {

    @Autowired
    AccessTokenUtility accessTokenUtility;

    private static final List<String> AUTHORIZED_ROLE = List.of("hr", "admin");

    public JobInterviewStatusDto createJobInterviewStatusDto(String name, String description, Boolean isActive) {

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(description) || isActive == null) {
            throw new IllegalArgumentException("the parameters for the creation of job interview status dto can't be " +
                    "null or empty");
        }
        return JobInterviewStatusDtoBuilder.newBuilder(name)
                .description(description)
                .isActive(isActive)
                .build();
    }

    public Boolean authorizeJobInterviewStatusService(String accessToken) {
        Set<Predicate<String>> predicateSet = accessTokenUtility.createPredicateSet(AUTHORIZED_ROLE);
        return accessTokenUtility.verifyAccountCredential(accessToken, predicateSet);
    }
}
