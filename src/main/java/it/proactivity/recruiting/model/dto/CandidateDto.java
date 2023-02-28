package it.proactivity.recruiting.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CandidateDto {

    private String fiscalCode;

    private String name;

    private String surname;

    private String cityOfBirth;

    private String countryOfBirth;

    private String cityOfResidence;

    private String streetOfResidence;

    private String regionOfResidence;

    private String countryOfResidence;

    private String email;

    private String phoneNumber;

    private String gender;

    private Boolean isActive;

    private String birthDate;

    @Override
    public String toString() {
        return name + " " + surname + " nato a : " + cityOfBirth + "\n"
                + "Residente a : " + cityOfResidence + " all'indirizzo : " + streetOfResidence + " nella regione: " +
                regionOfResidence + " (" + countryOfResidence + ")" + "\n"
                + "email : " + email + " phoneNumber : " + phoneNumber + " gender : " + gender + " data di nascita : " +
                birthDate;
    }
}
