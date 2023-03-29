package it.proactivity.recruiting.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto extends AnagraficalDto {

    private String companyName;

    private String companyRoleName;

    private Boolean isActive;

    public EmployeeDto(Long id, String fiscalCode, String name, String surname, String cityOfBirth, String countryOfBirth,
                       String cityOfResidence, String streetOfResidence, String regionOfResidence,
                       String countryOfResidence, String email, String phoneNumber, String gender, Boolean isActive,
                       String birthDate, String expertiseName, String companyName, String companyRoleName) {

        super(id, name, surname, fiscalCode, cityOfBirth, countryOfBirth, cityOfResidence, streetOfResidence, regionOfResidence,
                countryOfResidence, email, phoneNumber, gender, birthDate, expertiseName);
        this.companyName = companyName;
        this.companyRoleName = companyRoleName;
        this.isActive = isActive;
    }

    public EmployeeDto(String fiscalCode, String name, String surname, String cityOfBirth, String countryOfBirth,
                       String cityOfResidence, String streetOfResidence, String regionOfResidence,
                       String countryOfResidence, String email, String phoneNumber, String gender, Boolean isActive,
                       String birthDate, String expertiseName, String companyName, String companyRoleName) {

        super(name, surname, fiscalCode, cityOfBirth, countryOfBirth, cityOfResidence, streetOfResidence, regionOfResidence,
                countryOfResidence, email, phoneNumber, gender, birthDate, expertiseName);
        this.companyName = companyName;
        this.companyRoleName = companyRoleName;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return super.getName() + " " + super.getSurname() + " born to : " + super.getCityOfBirth() + "\n"
                + "Resident : " + super.getCityOfResidence() + " address : " + super.getStreetOfResidence() +
                " region : " + super.getRegionOfResidence() + " (" + super.getCountryOfResidence() + ")" + "\n" + "email : " +
                super.getEmail() + " phoneNumber : " + super.getPhoneNumber() + " gender : " + super.getGender() + " date of birth : " +
                super.getBirthDate();
    }
}
