package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.EmployeeDto;
import it.proactivity.recruiting.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    void getAllEmployeeTest() {
        Set<EmployeeDto> dtoList = employeeService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getEmployeeByIdTest() {
        EmployeeDto employeeDto = employeeService.findById(1l).getBody();
        assertNotNull(employeeDto);
        System.out.println(employeeDto);
    }
}
