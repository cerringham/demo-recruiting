package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CurriculumBuilder;
import it.proactivity.recruiting.builder.SkillBuilder;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CurriculumUtility {

    @Autowired
    CandidateUtility candidateUtility;

    @Autowired
    SkillUtility skillUtility;

    public Set<Curriculum> createCurriculumSetFromDto(Set<CurriculumDto> curriculumDtos) {
        Set<Curriculum> curriculumSet = curriculumDtos.stream()
                .map(c -> CurriculumBuilder.newBuilder(candidateUtility.createCandidateFromDto(c.getCandidateId()))
                        .skill(skillUtility.createSkillFromDto(c.getSkillId()))
                        .isActive(true)
                        .build())
                .collect(Collectors.toSet());
        return curriculumSet;
    }
}
