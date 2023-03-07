package it.proactivity.recruiting.model.dto;

import it.proactivity.recruiting.myEnum.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateInformationDto extends AnagraficalDto {

    private Map<String, Level> skillLevelMap = new HashMap<>();

    public CandidateInformationDto(String name, String surname, String fiscalCode, String cityOfBirth,
                                   String countryOfBirth, String cityOfResidence, String streetOfResidence,
                                   String regionOfResidence, String countryOfResidence, String email, String phoneNumber,
                                   String gender, String birthDate, String expertise, Map<String, Level> skillLevelMap) {

        super(name, surname, fiscalCode, cityOfBirth, countryOfBirth, cityOfResidence, streetOfResidence, regionOfResidence,
                countryOfResidence, email, phoneNumber, gender, birthDate, expertise);
        this.skillLevelMap = skillLevelMap;
    }

    public CandidateInformationDto(Long id, String name, String surname, String fiscalCode, String cityOfBirth, String countryOfBirth,
                                   String cityOfResidence, String streetOfResidence, String regionOfResidence,
                                   String countryOfResidence, String email, String phoneNumber, String gender,
                                   String birthDate, String expertiseName, Map<String, Level> skillLevelMap) {

        super(id, name, surname, fiscalCode, cityOfBirth, countryOfBirth, cityOfResidence, streetOfResidence, regionOfResidence,
                countryOfResidence, email, phoneNumber, gender, birthDate, expertiseName);
        this.skillLevelMap = skillLevelMap;
    }
}
