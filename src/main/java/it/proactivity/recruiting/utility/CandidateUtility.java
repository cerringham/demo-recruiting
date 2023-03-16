package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CandidateBuilder;
import it.proactivity.recruiting.builder.CandidateDtoBuilder;
import it.proactivity.recruiting.builder.CurriculumBuilder;
import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateInformationDto;
import it.proactivity.recruiting.project_enum.Level;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CandidateUtility {

    @Autowired
    ParsingUtility parsingUtility;

    @Autowired
    ExpertiseRepository expertiseRepository;


    public void setAllStringParametersForCandidate(CandidateInformationDto dto, Candidate candidate) {
        candidate.setName(dto.getName());
        candidate.setSurname(dto.getSurname());
        candidate.setPhoneNumber(dto.getPhoneNumber());
        candidate.setGender(dto.getGender());
        candidate.setEmail(dto.getEmail());
        candidate.setRegionOfResidence(dto.getRegionOfResidence());
        candidate.setCountryOfBirth(dto.getCountryOfBirth());
        candidate.setStreetOfResidence(dto.getStreetOfResidence());
        candidate.setCityOfBirth(dto.getCityOfBirth());
        candidate.setCityOfResidence(dto.getCityOfResidence());
        candidate.setCountryOfResidence(dto.getCountryOfResidence());
        candidate.setFiscalCode(dto.getFiscalCode());
    }

    public Candidate createCandidate(CandidateInformationDto dto) {
        Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCaseAndIsActive(dto.getExpertise(), true);
        if (expertise.isEmpty()) {
            throw new IllegalArgumentException("Expertise doesn't exists");
        }

        return CandidateBuilder.newBuilder(dto.getName())
                .surname(dto.getSurname())
                .fiscalCode(dto.getFiscalCode())
                .cityOfBirth(dto.getCityOfBirth())
                .countryOfBirth(dto.getCountryOfBirth())
                .cityOfResidence(dto.getCityOfResidence())
                .streetOfResidence(dto.getStreetOfResidence())
                .regionOfResidence(dto.getRegionOfResidence())
                .countryOfResidence(dto.getCountryOfResidence())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .gender(dto.getGender())
                .isActive(true)
                .birthDate(parsingUtility.parseStringToLocalDate(dto.getBirthDate()))
                .expertise(expertise.get())
                .build();
    }

    public CandidateDto createCandidateDto(String fiscalCode, String name, String surname, String cityOfBirth,
                                           String countryOfBirth, String cityOfResidence, String streetOfResidence,
                                           String regionOfResidence, String countryOfResidence, String email,
                                           String phoneNumber, String gender, Boolean isActive, String birthDate) {

        if (StringUtils.isEmpty(fiscalCode) || StringUtils.isEmpty(name) || StringUtils.isEmpty(surname) ||
                StringUtils.isEmpty(cityOfBirth) || StringUtils.isEmpty(countryOfBirth) ||
                StringUtils.isEmpty(cityOfResidence) || StringUtils.isEmpty(streetOfResidence) ||
                StringUtils.isEmpty(regionOfResidence) || StringUtils.isEmpty(countryOfResidence) ||
                StringUtils.isEmpty(email) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(gender) ||
                isActive == null || StringUtils.isEmpty(birthDate)) {

            throw new IllegalArgumentException("The data's for creating the candidate dto are empty or null");
        }

        return CandidateDtoBuilder.newBuilder(name)
                .fiscalCode(fiscalCode)
                .surname(surname)
                .cityOfBirth(cityOfBirth)
                .countryOfBirth(countryOfBirth)
                .cityOfResidence(cityOfResidence)
                .streetOfResidence(streetOfResidence)
                .regionOfResidence(regionOfResidence)
                .countryOfResidence(countryOfResidence)
                .email(email)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .isActive(isActive)
                .birthDate(birthDate)
                .build();
    }

    public List<Curriculum> createCurriculumList(Map<Skill, Level> skillLevelMap,
                                                 Candidate candidate) {

        List<Curriculum> curriculumList = new ArrayList<>();
        for (Map.Entry<Skill, Level> entry : skillLevelMap.entrySet()) {
            Curriculum curriculum = CurriculumBuilder.newBuilder(candidate)
                    .skill(entry.getKey())
                    .level(entry.getValue())
                    .isActive(true)
                    .build();
            curriculumList.add(curriculum);
        }
        return curriculumList;
    }
}
