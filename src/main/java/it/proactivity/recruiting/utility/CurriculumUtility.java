package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CurriculumDtoBuilder;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CurriculumUtility {

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
}
