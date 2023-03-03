package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.ExpertiseBuilder;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.dto.ExpertiseDto;
import org.springframework.stereotype.Component;

@Component
public class ExpertiseUtility {

    public Expertise createExpertiseFromDto(ExpertiseDto expertiseDto) {
        return ExpertiseBuilder.newBuilder(expertiseDto.getId())
                .name(expertiseDto.getName())
                .isActive(expertiseDto.getIsActive())
                .build();
    }
}
