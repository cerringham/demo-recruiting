package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.ExpertiseDtoBuilder;
import it.proactivity.recruiting.model.dto.ExpertiseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class ExpertiseUtility {


    @Autowired
    AccessTokenUtility accessTokenUtility;

    private static final List<String> AUTHORIZED_ROLE = List.of("hr");

    public ExpertiseDto createExpertiseDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("the parameters for creating the expertise dto can't be null or empty");
        }

        return ExpertiseDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }

    public Boolean authorizeExpertiseService(String accessToken) {
        Set<Predicate<String>> predicateSet = accessTokenUtility.createPredicateSet(AUTHORIZED_ROLE);
        return accessTokenUtility.verifyAccountCredential(accessToken, predicateSet);
    }
}
