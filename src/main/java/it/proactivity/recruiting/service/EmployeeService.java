package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.EmployeeDtoBuilder;
import it.proactivity.recruiting.model.Employee;
import it.proactivity.recruiting.model.dto.EmployeeDto;
import it.proactivity.recruiting.repository.EmployeeRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.ParsingUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<Set<EmployeeDto>> getAll() {
        List<Employee> employeeList = employeeRepository.findAll();

        Set<EmployeeDto> dtoList = employeeList.stream()
                .map(e -> createEmployeeDto(e.getFiscalCode(), e.getName(), e.getSurname(), e.getCityOfBirth(),
                        e.getCountryOfBirth(), e.getCityOfResidence(), e.getStreetOfResidence(), e.getRegionOfResidence(),
                        e.getCountryOfResidence(), e.getEmail(), e.getPhoneNumber(), e.getGender(), e.getIsActive(),
                        parsingUtility.parseDateToString(e.getBirthDate())))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<EmployeeDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createEmployeeDto(employee.get().getFiscalCode(), employee.get().getName(), employee.get().getSurname(),
                employee.get().getCityOfBirth(), employee.get().getCountryOfBirth(), employee.get().getCityOfResidence(),
                employee.get().getStreetOfResidence(), employee.get().getRegionOfResidence(),
                employee.get().getCountryOfResidence(), employee.get().getEmail(), employee.get().getPhoneNumber(),
                employee.get().getGender(), employee.get().getIsActive(),
                parsingUtility.parseDateToString(employee.get().getBirthDate())));
    }

    private EmployeeDto createEmployeeDto(String fiscalCode, String name, String surname, String cityOfBirth,
                                          String countryOfBirth, String cityOfResidence, String streetOfResidence,
                                          String regionOfResidence, String countryOfResidence, String email,
                                          String phoneNumber, String gender, Boolean isActive, String birthDate) {

        if (StringUtils.isEmpty(fiscalCode) || StringUtils.isEmpty(name) || StringUtils.isEmpty(surname) ||
                StringUtils.isEmpty(cityOfBirth) || StringUtils.isEmpty(countryOfBirth) ||
                StringUtils.isEmpty(cityOfResidence) || StringUtils.isEmpty(streetOfResidence) ||
                StringUtils.isEmpty(regionOfResidence) || StringUtils.isEmpty(countryOfResidence) ||
                StringUtils.isEmpty(email) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(gender) ||
                isActive == null || StringUtils.isEmpty(birthDate)) {

            throw new IllegalArgumentException("The data's for creating the employee dto are empty or null");
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
                .build();
    }

}
