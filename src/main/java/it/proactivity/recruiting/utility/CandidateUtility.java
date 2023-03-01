package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.CandidateBuilder;
import it.proactivity.recruiting.builder.CandidateDtoBuilder;
import it.proactivity.recruiting.builder.CurriculumBuilder;
import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateWithSkillDto;
import it.proactivity.recruiting.myEnum.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidateUtility {
    @Autowired
    ParsingUtility parsingUtility;

    @Autowired
    CandidateValidator candidateValidator;

    public CandidateDto createCandidateDto(String fiscalCode, String name, String surname, String cityOfBirth,
                                           String countryOfBirth, String cityOfResidence, String streetOfResidence,
                                           String regionOfResidence, String countryOfResidence, String email,
                                           String phoneNumber, String gender, Boolean isActive, String birthDate) {

        if (!candidateValidator.validateParameters(fiscalCode, name, surname, cityOfBirth, countryOfBirth,
                cityOfResidence, streetOfResidence, regionOfResidence, countryOfResidence, email, phoneNumber, gender,
                isActive, birthDate)) {
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
    public Candidate createCandidateFromCandidateWithSkillDto(CandidateWithSkillDto candidateDto) {
        return CandidateBuilder.newBuilder(candidateDto.getName())
                .fiscalCode(candidateDto.getFiscalCode())
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
                .expertise(candidateDto.getExpertise())
                .curriculumDtoList(candidateDto.getCurriculumList())
                .build();
    }

    public Curriculum createCurriculum(Candidate candidate, Skill skill, Level level) {
        if (candidate == null || skill == null || level == null) {
            throw new IllegalArgumentException();
        }
        return CurriculumBuilder.newBuilder(candidate)
                .skill(skill)
                .level(level)
                .build();
    }
}
