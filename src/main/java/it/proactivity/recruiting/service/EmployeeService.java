package it.proactivity.recruiting.service;

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
import it.proactivity.recruiting.utility.EmployeeValidator;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.ParsingUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ParsingUtility parsingUtility;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    EmployeeValidator employeeValidator;

    @Autowired
    ExpertiseRepository expertiseRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyRoleRepository companyRoleRepository;

    public ResponseEntity<Set<EmployeeDto>> getAll() {
        List<Employee> employeeList = employeeRepository.findByIsActive(true);

        Set<EmployeeDto> dtoList = employeeList.stream()
                .map(e -> createEmployeeDto(e.getFiscalCode(), e.getName(), e.getSurname(), e.getCityOfBirth(),
                        e.getCountryOfBirth(), e.getCityOfResidence(), e.getStreetOfResidence(), e.getRegionOfResidence(),
                        e.getCountryOfResidence(), e.getEmail(), e.getPhoneNumber(), e.getGender(), e.getIsActive(),
                        parsingUtility.parseDateToString(e.getBirthDate()), e.getExpertise().getName(),
                        e.getCompany().getName(), e.getCompanyRole().getName()))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<EmployeeDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<Employee> employee = employeeRepository.findByIdAndIsActive(id, true);

        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createEmployeeDto(employee.get().getFiscalCode(), employee.get().getName(), employee.get().getSurname(),
                employee.get().getCityOfBirth(), employee.get().getCountryOfBirth(), employee.get().getCityOfResidence(),
                employee.get().getStreetOfResidence(), employee.get().getRegionOfResidence(),
                employee.get().getCountryOfResidence(), employee.get().getEmail(), employee.get().getPhoneNumber(),
                employee.get().getGender(), employee.get().getIsActive(),
                parsingUtility.parseDateToString(employee.get().getBirthDate()), employee.get().getExpertise().getName(),
                employee.get().getCompany().getName(), employee.get().getCompanyRole().getName()));
    }

    public ResponseEntity insertEmployee(EmployeeDto dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dto can't be null");
        }

        if (!globalValidator.validateStringAlphaSpace(dto.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name must be alpha space");
        }

        if (!globalValidator.validateStringAlphaSpace(dto.getSurname())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Surname must be alpha space");
        }

        if (!globalValidator.validateEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email format is wrong");
        }

        if (!globalValidator.validatePhoneNumber(dto.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PhoneNumber format is wrong");
        }

        if (!globalValidator.validateAge(dto.getBirthDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Employee's age must be at least 18");
        }

        if (!employeeValidator.validateCompanyRole(dto.getCompanyRoleName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Employee's role can't be null or empty");
        }

        //Controllo se gli employee hanno già un ceo oppure se ogni compagnia ha un coo.
        if (!checkIfExistCeoOrCoo(dto.getCompanyRoleName(), dto.getCompanyName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Employee employee;
        try {
            employee = createEmployee(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity deleteEmployee(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id can't be null");
        }

        Optional<Employee> employee = employeeRepository.findByIdAndIsActive(id, true);

        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        employee.get().setIsActive(false);
        employeeRepository.save(employee.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity updateEmployee(EmployeeDto dto) {

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dto can't be null");
        }

        Optional<Employee> employee = employeeRepository.findByIdAndIsActive(dto.getId(), true);
        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCaseAndIsActive(dto.getExpertiseName(), true);
        if (expertise.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expertise not found");
        }

        Optional<Company> company = companyRepository.findByNameIgnoreCaseAndIsActive(dto.getCompanyName(), true);
        if (company.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }

        Optional<CompanyRole> companyRole = companyRoleRepository.findByNameIgnoreCaseAndIsActive(dto.getCompanyRoleName(), true);
        if (companyRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CompanyRole not found");
        }

        LocalDate parsedDate = parsingUtility.parseStringToLocalDate(dto.getBirthDate());
        if (parsedDate == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Date format is wrong");
        }

        if (!checkIfExistCeoOrCoo(dto.getCompanyRoleName(), dto.getCompanyName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        setAllStringParameters(employee.get(), dto);

        employee.get().setBirthDate(parsedDate);
        employee.get().setExpertise(expertise.get());
        employee.get().setCompany(company.get());
        employee.get().setCompanyRole(companyRole.get());
        employeeRepository.save(employee.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private void setAllStringParameters(Employee employee, EmployeeDto dto) {
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

    private Boolean checkIfExistCeoOrCoo(String companyRoleName, String companyName) {
        if (StringUtils.isEmpty(companyRoleName) || StringUtils.isEmpty(companyName)) {
            return false;
        }
        Optional<Company> company = companyRepository.findByNameIgnoreCaseAndIsActive(companyName, true);
        if (company.isEmpty()) {
            return false;
        }

        if (companyRoleName.equalsIgnoreCase("ceo")) {
            Long numberOfCeo = employeeRepository.countNumberOfCeo();
            if (numberOfCeo > 0) {
                return false;
            } else {
                return true;
            }
        }

        if (companyRoleName.equalsIgnoreCase("coo")) {

            Long verifyCooExistance = companyRepository.countNumberOfCeo(company.get().getId());
            if (verifyCooExistance != null) {
                return false;
            } else {
                return true;
            }
        }

        return true;
    }

    private EmployeeDto createEmployeeDto(String fiscalCode, String name, String surname, String cityOfBirth,
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

    private Employee createEmployee(EmployeeDto dto) {

        Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCaseAndIsActive(dto.getExpertiseName(), true);
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

}
