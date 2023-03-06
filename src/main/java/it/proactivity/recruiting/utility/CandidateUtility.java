package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CandidateBuilder;
import it.proactivity.recruiting.builder.CandidateDtoBuilder;
import it.proactivity.recruiting.builder.CurriculumBuilder;
import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateWithSkillDto;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.myEnum.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CandidateUtility {
    @Autowired
    ParsingUtility parsingUtility;
    @Autowired
    ExpertiseUtility expertiseUtility;

    @Autowired
    SkillUtility skillUtility;

    public CandidateDto createCandidateDto(Candidate candidate) {

        if (candidate == null) {
            throw new IllegalArgumentException("The data's for creating the candidate dto are empty or null");
        }
        return CandidateDtoBuilder.newBuilder(candidate.getFiscalCode())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .cityOfBirth(candidate.getCityOfBirth())
                .countryOfBirth(candidate.getCountryOfBirth())
                .cityOfResidence(candidate.getCityOfResidence())
                .streetOfResidence(candidate.getStreetOfResidence())
                .regionOfResidence(candidate.getRegionOfResidence())
                .countryOfResidence(candidate.getCountryOfResidence())
                .email(candidate.getEmail())
                .phoneNumber(candidate.getPhoneNumber())
                .gender(candidate.getGender())
                .isActive(candidate.getIsActive())
                .birthDate(parsingUtility.parseDateToString(candidate.getBirthDate()))
                .build();
    }
    public Candidate createCandidateFromCandidateWithSkillDto(CandidateWithSkillDto candidateDto) {
        return CandidateBuilder.newBuilder(candidateDto.getFiscalCode())
                .name(candidateDto.getName())
                .surname(candidateDto.getSurname())
                .cityOfBirth(candidateDto.getCityOfBirth())
                .countryOfBirth(candidateDto.getCountryOfBirth())
                .cityOfResidence(candidateDto.getCityOfResidence())
                .streetOfResidence(candidateDto.getStreetOfResidence())
                .regionOfResidence(candidateDto.getRegionOfResidence())
                .countryOfResidence(candidateDto.getCountryOfResidence())
                .email(candidateDto.getEmail())
                .phoneNumber(candidateDto.getPhoneNumber())
                .gender(candidateDto.getGender())
                .isActive(candidateDto.getIsActive())
                .birthDate(parsingUtility.parseStringToDate(candidateDto.getBirthDate()))
                .expertise(expertiseUtility.createExpertiseFromDto(candidateDto.getExpertise()))
                .build();
    }

    public Candidate createCandidateFromDto(CandidateDto candidateDto) {
        return CandidateBuilder.newBuilder(candidateDto.getFiscalCode())
                .name(candidateDto.getName())
                .surname(candidateDto.getSurname())
                .cityOfBirth(candidateDto.getCityOfBirth())
                .countryOfBirth(candidateDto.getCountryOfBirth())
                .cityOfResidence(candidateDto.getCityOfResidence())
                .streetOfResidence(candidateDto.getStreetOfResidence())
                .regionOfResidence(candidateDto.getRegionOfResidence())
                .countryOfResidence(candidateDto.getCountryOfResidence())
                .email(candidateDto.getEmail())
                .phoneNumber(candidateDto.getPhoneNumber())
                .gender(candidateDto.getGender())
                .isActive(candidateDto.getIsActive())
                .birthDate(parsingUtility.parseStringToDate(candidateDto.getBirthDate()))
                .build();
    }
    public Set<Curriculum> createCurriculumSetFromParameters(Candidate candidate, Skill skill, Level level) {
        if (candidate == null || skill == null || level == null) {
            throw new IllegalArgumentException();
        }
        Set<Curriculum> curriculumSet = new HashSet<>();
        curriculumSet.add(CurriculumBuilder.newBuilder(candidate)
                .skill(skill)
                .level(level)
                .build());
        return curriculumSet;
    }

    public Set<Curriculum> createCurriculumSetFromDto(Set<CurriculumDto> curriculumDtos) {
        Set<Curriculum> curriculumSet = curriculumDtos.stream()
                .map(c -> CurriculumBuilder.newBuilder(createCandidateFromDto(c.getCandidateDto()))
                        .skill(skillUtility.createSkillFromDto(c.getSkillDto()))
                        .isActive(true)
                        .build())
                .collect(Collectors.toSet());
        return curriculumSet;
    }
}
