package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateWithSkillDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public Boolean validateCandidate(CandidateWithSkillDto candidateWithSkillDto) {
        if (!globalValidator.validateFiscalCode(candidateWithSkillDto.getFiscalCode()) ||
                !globalValidator.validateNameOrSurname(candidateWithSkillDto.getName()) ||
                !globalValidator.validateNameOrSurname(candidateWithSkillDto.getSurname()) ||
                !globalValidator.validateBirthDate(candidateWithSkillDto.getBirthDate()) ||
                !globalValidator.validateNameOrSurname(candidateWithSkillDto.getCityOfBirth()) ||
                !globalValidator.validateNameOrSurname(candidateWithSkillDto.getCountryOfBirth()) ||
                !globalValidator.validateNameOrSurname(candidateWithSkillDto.getCityOfResidence()) ||
                !globalValidator.validateNameOrSurname(candidateWithSkillDto.getRegionOfResidence()) ||
                !globalValidator.validateEmail(candidateWithSkillDto.getEmail()) ||
                globalValidator.validatePhoneNumber(candidateWithSkillDto.getPhoneNumber()) ||
                StringUtils.isEmpty(candidateWithSkillDto.getGender())) {
            return false;
        }
        return true;
    }

}
