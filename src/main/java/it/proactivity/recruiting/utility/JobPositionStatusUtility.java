package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobPositionStatusDtoBuilder;
import it.proactivity.recruiting.model.dto.JobPositionStatusDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class JobPositionStatusUtility {

    @Autowired
    AccessTokenUtility accessTokenUtility;

    private static final List<String> AUTHORIZED_ROLE = List.of("hr", "admin");


    public JobPositionStatusDto createJobPositionStatusDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of JobPositionStatusDto can't be null or empty");
        }

        return JobPositionStatusDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }

    public Boolean authorizeJobPositionStatusService(String accessToken) {
        Set<Predicate<String>> predicateSet = accessTokenUtility.createPredicateSet(AUTHORIZED_ROLE);
        return accessTokenUtility.verifyAccountCredential(accessToken, predicateSet);
    }
}
