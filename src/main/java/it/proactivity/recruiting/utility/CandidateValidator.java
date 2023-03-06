package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.dto.CandidateWithSkillDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//Il candidato ha tutte le informazioni valorizzate.
//Nome e cognome hanno solo caratteri alfabetici e spazi.
//La mail deve essere formattata correttamente.
//Il numero di telefono ha solo cifre e il + per il prefisso internazionale.
//Il candidato deve avere almeno 18 anni (farà fede la data di nascita)
//Il candidato ha almeno una skill.
//Se la skill non esiste nel nostro database (match case insensitive) allora viene salvata anche la nuova skill.
//Viene creata una entry nella tabella Curriculum per ogni Skill associata al candidate.
@Component
public class CandidateValidator {

    @Autowired
    GlobalValidator globalValidator;

    public Boolean validateParameters(String fiscalCode, String name, String surname, String cityOfBirth,
                                            String countryOfBirth, String cityOfResidence, String streetOfResidence,
                                            String regionOfResidence, String countryOfResidence, String email,
                                            String phoneNumber, String gender, Boolean isActive, String birthDate) {

        if (StringUtils.isEmpty(fiscalCode) || StringUtils.isEmpty(name) || StringUtils.isEmpty(surname) ||
                StringUtils.isEmpty(cityOfBirth) || StringUtils.isEmpty(countryOfBirth) ||
                StringUtils.isEmpty(cityOfResidence) || StringUtils.isEmpty(streetOfResidence) ||
                StringUtils.isEmpty(regionOfResidence) || StringUtils.isEmpty(countryOfResidence) ||
                StringUtils.isEmpty(email) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(gender) ||
                isActive == null || StringUtils.isEmpty(birthDate)) {
            return false;
        }
        return true;
    }

    public Boolean validateCandidate(CandidateWithSkillDto candidateWithSkillDto) {
        if (!globalValidator.validateFiscalCode(candidateWithSkillDto.getFiscalCode()) ||
                !globalValidator.validateAlphaSpace(candidateWithSkillDto.getName()) ||
                !globalValidator.validateAlphaSpace(candidateWithSkillDto.getSurname()) ||
                !globalValidator.validateBirthDate(candidateWithSkillDto.getBirthDate()) ||
                !globalValidator.validateAlphaSpace(candidateWithSkillDto.getCityOfBirth()) ||
                !globalValidator.validateAlphaSpace(candidateWithSkillDto.getCountryOfBirth()) ||
                !globalValidator.validateAlphaSpace(candidateWithSkillDto.getCityOfResidence()) ||
                !globalValidator.validateAlphaSpace(candidateWithSkillDto.getRegionOfResidence()) ||
                !globalValidator.validateEmail(candidateWithSkillDto.getEmail()) ||
                !globalValidator.validatePhoneNumber(candidateWithSkillDto.getPhoneNumber()) ||
                StringUtils.isEmpty(candidateWithSkillDto.getGender()) ||
                candidateWithSkillDto.getExpertise() == null) {
            return false;
        }
        return true;
    }

}
