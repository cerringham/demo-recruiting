package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CurriculumDtoBuilder;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class CurriculumUtility {

    @Autowired
    AccessTokenUtility accessTokenUtility;

    private static final List<String> AUTHORIZED_ROLE = List.of("hr");

    public CurriculumDto createCurriculumDto(Long candidateId, String candidateName, String candidateSurname,
                                             String skillName, String level) {
        if (candidateId == null || StringUtils.isEmpty(candidateName) || StringUtils.isEmpty(candidateSurname) ||
                StringUtils.isEmpty(skillName) || StringUtils.isEmpty(level)) {
            throw new IllegalArgumentException("The parameters for the creation of curriculum dto can't be null or empty");
        }

        return CurriculumDtoBuilder.newBuilder(candidateId)
                .candidateName(candidateName)
                .candidateSurname(candidateSurname)
                .skillName(skillName)
                .level(level)
                .build();
    }

    public Boolean authorizeCurriculumService(String accessToken) {
        Set<Predicate<String>> predicateSet = accessTokenUtility.createPredicateSet(AUTHORIZED_ROLE);
        return accessTokenUtility.verifyAccountCredential(accessToken, predicateSet);
    }
}
