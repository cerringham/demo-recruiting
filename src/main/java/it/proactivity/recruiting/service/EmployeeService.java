package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.CompanyRole;
import it.proactivity.recruiting.model.Employee;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.dto.EmployeeDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.repository.CompanyRoleRepository;
import it.proactivity.recruiting.repository.EmployeeRepository;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import it.proactivity.recruiting.utility.EmployeeUtility;
import it.proactivity.recruiting.utility.EmployeeValidator;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.ParsingUtility;
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
    GlobalValidator globalValidator;

    @Autowired
    EmployeeValidator employeeValidator;

    @Autowired
    EmployeeUtility employeeUtility;

    @Autowired
    ParsingUtility parsingUtility;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ExpertiseRepository expertiseRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyRoleRepository companyRoleRepository;

    public ResponseEntity<Set<EmployeeDto>> getAll() {
        List<Employee> employeeList = employeeRepository.findByIsActive(true);

        Set<EmployeeDto> dtoList = employeeList.stream()
                .map(e -> employeeUtility.createEmployeeDto(e.getFiscalCode(), e.getName(), e.getSurname(), e.getCityOfBirth(),
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

        return ResponseEntity.ok(employeeUtility.createEmployeeDto(employee.get().getFiscalCode(), employee.get().getName(), employee.get().getSurname(),
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
        if (!employeeUtility.checkIfExistCeoOrCoo(dto.getCompanyRoleName(), dto.getCompanyName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Employee employee;
        try {
            employee = employeeUtility.createEmployee(dto);
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
        employeeRepository.deleteEmployee(id);
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

        Optional<Expertise> expertise = expertiseRepository.findByNameIgnoreCaseAndIsActive(dto.getExpertise(), true);
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

        if (!employeeUtility.checkIfExistCeoOrCoo(dto.getCompanyRoleName(), dto.getCompanyName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        employeeUtility.setAllStringParameters(employee.get(), dto);

        employee.get().setBirthDate(parsedDate);
        employee.get().setExpertise(expertise.get());
        employee.get().setCompany(company.get());
        employee.get().setCompanyRole(companyRole.get());
        employeeRepository.save(employee.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
