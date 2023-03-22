package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.EmployeeBuilder;
import it.proactivity.recruiting.builder.EmployeeDtoBuilder;
import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.CompanyRole;
import it.proactivity.recruiting.model.Employee;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.dto.EmployeeDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.repository.CompanyRoleRepository;
import it.proactivity.recruiting.repository.EmployeeRepository;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class EmployeeUtility {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ParsingUtility parsingUtility;

    @Autowired
    ExpertiseRepository expertiseRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyRoleRepository companyRoleRepository;

    public void setAllStringParameters(Employee employee, EmployeeDto dto) {
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setCityOfBirth(dto.getCityOfBirth());
        employee.setCountryOfBirth(dto.getCountryOfBirth());
        employee.setCityOfResidence(dto.getCityOfResidence());
        employee.setCountryOfResidence(dto.getCountryOfResidence());
        employee.setStreetOfResidence(dto.getStreetOfResidence());
        employee.setRegionOfResidence(dto.getRegionOfResidence());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setGender(dto.getGender());
        employee.setFiscalCode(dto.getFiscalCode());
    }

    public Boolean checkIfExistCeoOrCoo(String companyRoleName, String companyName) {
        if (StringUtils.isEmpty(companyRoleName) || StringUtils.isEmpty(companyName)) {
            return false;
        }
        Optional<Company> company = companyRepository.findByNameIgnoreCaseAndIsActive(companyName, true);
        if (company.isEmpty()) {
            return false;
        }

        if (companyRoleName.equalsIgnoreCase("ceo")) {
            Long numberOfCeo = employeeRepository.countNumberOfCeo();
            return numberOfCeo <= 0;
        }

        if (companyRoleName.equalsIgnoreCase("coo")) {

            Long verifyCooExistence = companyRepository.checkIfCooExists(company.get().getId());
            return verifyCooExistence == null;
        }

        return true;
    }

    public EmployeeDto createEmployeeDto(String fiscalCode, String name, String surname, String cityOfBirth,
                                         String countryOfBirth, String cityOfResidence, String streetOfResidence,
                                         String regionOfResidence, String countryOfResidence, String email,
                                         String phoneNumber, String gender, Boolean isActive, String birthDate,
                                         String expertiseName, String companyName, String companyRoleName) {

        if (StringUtils.isEmpty(fiscalCode) || StringUtils.isEmpty(name) || StringUtils.isEmpty(surname) ||
                StringUtils.isEmpty(cityOfBirth) || StringUtils.isEmpty(countryOfBirth) ||
                StringUtils.isEmpty(cityOfResidence) || StringUtils.isEmpty(streetOfResidence) ||
                StringUtils.isEmpty(regionOfResidence) || StringUtils.isEmpty(countryOfResidence) ||
                StringUtils.isEmpty(email) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(gender) ||
                isActive == null || StringUtils.isEmpty(birthDate)) {

            return null;
        }

        return EmployeeDtoBuilder.newBuilder(name)
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
                .expertiseName(expertiseName)
                .companyName(companyName)
                .companyRoleName(companyRoleName)
                .build();
    }

    public Employee createEmployee(EmployeeDto dto) {

        Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCaseAndIsActive(dto.getExpertise(), true);
        if (expertise.isEmpty()) {
            throw new IllegalArgumentException("Expertise not found");
        }

        Optional<Company> company = companyRepository.findByNameIgnoreCaseAndIsActive(dto.getCompanyName(), true);
        if (company.isEmpty()) {
            throw new IllegalArgumentException("Company not found");
        }

        Optional<CompanyRole> companyRole = companyRoleRepository.findByNameIgnoreCaseAndIsActive(dto.getCompanyRoleName(),
                true);
        if (companyRole.isEmpty()) {
            throw new IllegalArgumentException("CompanyRole not found");
        }

        LocalDate parsedBirthDate = parsingUtility.parseStringToLocalDate(dto.getBirthDate());
        if (parsedBirthDate == null) {
            throw new NullPointerException("Impossible to parse the date");
        }

        return EmployeeBuilder.newBuilder(dto.getName())
                .surname(dto.getSurname())
                .fiscalCode(dto.getFiscalCode())
                .cityOfBirth(dto.getCityOfBirth())
                .countryOfBirth(dto.getCountryOfBirth())
                .cityOfResidence(dto.getCityOfResidence())
                .countryOfResidence(dto.getCountryOfResidence())
                .streetOfResidence(dto.getStreetOfResidence())
                .regionOfResidence(dto.getRegionOfResidence())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .gender(dto.getGender())
                .company(company.get())
                .companyRole(companyRole.get())
                .expertise(expertise.get())
                .isActive(true)
                .birthDate(parsedBirthDate)
                .build();
    }

    public Employee getEmployeeFromId(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        return null;
    }
}
