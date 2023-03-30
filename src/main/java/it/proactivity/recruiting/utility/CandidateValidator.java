package it.proactivity.recruiting.utility;


import it.proactivity.recruiting.model.dto.CandidateInformationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CandidateValidator {

    @Autowired
    GlobalValidator globalValidator;

    public boolean validateSkill(Set<String> skills) {
        return skills != null && !skills.isEmpty();
    }

    public Boolean validateCandidateInformationDtoParameters(CandidateInformationDto dto) {
        if (dto == null) {
            return false;
        }
        return globalValidator.validateStringAlphaSpace(dto.getName()) &&
                globalValidator.validateStringAlphaSpace(dto.getSurname()) &&
                globalValidator.validateEmail(dto.getEmail()) && globalValidator.validateAge(dto.getBirthDate()) &&
                globalValidator.validatePhoneNumber(dto.getPhoneNumber());
    }
}
