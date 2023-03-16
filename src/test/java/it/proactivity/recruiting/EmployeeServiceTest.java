package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Employee;
import it.proactivity.recruiting.model.dto.EmployeeDto;
import it.proactivity.recruiting.repository.EmployeeRepository;
import it.proactivity.recruiting.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;


@SpringBootTest
 class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void getAllEmployeeTest() {
        Set<EmployeeDto> dtoList = employeeService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getEmployeeByIdTest() {
        EmployeeDto employeeDto = employeeService.findById(1L).getBody();
        assertNotNull(employeeDto);
        System.out.println(employeeDto);
    }

    @Test
    void insertEmployeePositiveTest() {

        EmployeeDto dto = new EmployeeDto("RTRTYU89U76G453W", "Barbara", "Buscetta", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "barbara.buscetta@gmail.it", "+39 7837493029", "f",
                true, "1995-12-09", "junior", "fortitude", "hr");


        long numberOfEmployeeBeforeInsert = employeeRepository.findByIsActive(true).size();

        employeeService.insertEmployee(dto);

        long numberOfEmployeeAfterInsert = employeeRepository.findByIsActive(true).size();

        assertTrue(numberOfEmployeeBeforeInsert < numberOfEmployeeAfterInsert);

    }

    @Test
    void insertEmployeeNulldtoNegativeTest() {
        ResponseEntity response = employeeService.insertEmployee(null);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeNullNameNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", null, "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-09", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeEmptyNameNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-09", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeNullSurnameNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", null, "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-09", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeNullEmailNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", null, "+39 8763483928", "m", true,
                "1995-12-09", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeEmptyEmailNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "", "+39 8763483928", "m", true,
                "1995-12-09", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeWrongEmailNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "@email", "+39 8763483928", "m", true,
                "1995-12-09", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeNullPhoneNumberNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", null, "m", true,
                "1995-12-09", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeEmptyPhoneNumberNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "", "m", true,
                "1995-12-09", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeWrongPhoneNumberNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "7366477www", "m", true,
                "1995-12-09", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeEmptySurnameNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-09", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeNullBirthDateNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, null, "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeEmptyBirthDateNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeWrongBirthDateNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "12/12/1998", "junior", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeNullCompanyRoleNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-12", "junior", "fortitude", null);


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeEmptyCompanyRoleNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-12", "junior", "fortitude", "");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeCeoCompanyRoleNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-12", "junior", "fortitude", "CEO");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeCooCompanyRoleNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-12", "junior", "proactivity", "COO");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeExpertiseNotFoundNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-09", "fantastic", "fortitude", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeCompanyNotFoundNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-09", "junior", "fortitude2", "coo");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertEmployeeCompanyRoleNotFoundNegativeTest() {

        EmployeeDto dto = new EmployeeDto("FDRETU09O87L222I", "Gigi", "Castello", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia",
                "Italia", "gigi.castello@gmail.it", "+39 8763483928", "m",
                true, "1995-12-09", "junior", "fortitude", "cio");


        ResponseEntity response = employeeService.insertEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void deleteEmployeePositiveTest() {

        long numberOfEmployeeBeforeDelete = employeeRepository.findByIsActive(true).size();

        employeeService.deleteEmployee(3L);

        long numberOfEmployeeAfterDelete = employeeRepository.findByIsActive(true).size();

        assertTrue(numberOfEmployeeBeforeDelete > numberOfEmployeeAfterDelete);
    }

    @Test
    void deleteEmployeeNegativeTestTest() {

        ResponseEntity response = employeeService.deleteEmployee(null);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void deleteEmployeeNotFoundNegativeTestTest() {

        ResponseEntity response = employeeService.deleteEmployee(100L);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }


    @Test
    void updateEmployeePositiveTest() {
        EmployeeDto dto = new EmployeeDto(4L, "IJKUHG67J99Y111T", "Ludovico", "Brignani",
                "Siracusa", "Italia", "Siracusa", "via roma 123",
                "Sicilia", "Italia", "ludovico.brignani@email.it",
                "+39 2229987654", "m", true, "1985-08-12", "junior",
                "proactivity", "Software enginer");

        employeeService.updateEmployee(dto);

        Optional<Employee> employee = employeeRepository.findByIdAndIsActive(4L, true);
        employee.ifPresent(value -> assertTrue(value.getCompanyRole().getName().equals("Software engineer")));
    }

    @Test
    void updateEmployeeCooAlreadyExistsNegativeTest() {
        EmployeeDto dto = new EmployeeDto(4L, "IJKUHG67J99Y111T", "Ludovico", "Brignani",
                "Siracusa", "Italia", "Siracusa", "via roma 123",
                "Sicilia", "Italia", "ludovico.brignani@email.it",
                "+39 2229987654", "m", true, "1985-08-12", "junior",
                "proactivity", "coo");

        employeeService.updateEmployee(dto);

        Optional<Employee> employee = employeeRepository.findByIdAndIsActive(4L, true);

        employee.ifPresent(value -> assertFalse(value.getCompanyRole().getName().equals("COO")));
    }

    @Test
    void updateEmployeeNotFoundNegativeTest() {
        EmployeeDto dto = new EmployeeDto(100L, "IJKUHG67J99Y111T", "Ludovico", "Brignani",
                "Siracusa", "Italia", "Siracusa", "via roma 123",
                "Sicilia", "Italia", "ludovico.brignani@email.it",
                "+39 2229987654", "m", true, "1985-08-12", "junior",
                "proactivity", "coo");

        ResponseEntity response = employeeService.updateEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void updateEmployeeExpertiseNotFoundNegativeTest() {
        EmployeeDto dto = new EmployeeDto(4L, "IJKUHG67J99Y111T", "Ludovico", "Brignani",
                "Siracusa", "Italia", "Siracusa", "via roma 123",
                "Sicilia", "Italia", "ludovico.brignani@email.it",
                "+39 2229987654", "m", true, "1985-08-12", "fantastic",
                "proactivity", "coo");

        ResponseEntity response = employeeService.updateEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void updateEmployeeCompanyNotFoundNegativeTest() {
        EmployeeDto dto = new EmployeeDto(4L, "IJKUHG67J99Y111T", "Ludovico", "Brignani",
                "Siracusa", "Italia", "Siracusa", "via roma 123",
                "Sicilia", "Italia", "ludovico.brignani@email.it",
                "+39 2229987654", "m", true, "1985-08-12", "junior",
                "proactivity2", "coo");

        ResponseEntity response = employeeService.updateEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void updateEmployeeCompanyRoleNotFoundNegativeTest() {
        EmployeeDto dto = new EmployeeDto(4L, "IJKUHG67J99Y111T", "Ludovico", "Brignani",
                "Siracusa", "Italia", "Siracusa", "via roma 123",
                "Sicilia", "Italia", "ludovico.brignani@email.it",
                "+39 2229987654", "m", true, "1985-08-12", "junior",
                "proactivity2", "cooo");

        ResponseEntity response = employeeService.updateEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void updateEmployeeWrongDateFormatNegativeTest() {
        EmployeeDto dto = new EmployeeDto(4L, "IJKUHG67J99Y111T", "Ludovico", "Brignani",
                "Siracusa", "Italia", "Siracusa", "via roma 123",
                "Sicilia", "Italia", "ludovico.brignani@email.it",
                "+39 2229987654", "m", true, "1985/08/12", "junior",
                "proactivity", "coo");

        ResponseEntity response = employeeService.updateEmployee(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }
}
