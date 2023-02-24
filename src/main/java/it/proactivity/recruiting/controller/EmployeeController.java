package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.EmployeeDto;
import it.proactivity.recruiting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/get-all-employees")
    public ResponseEntity<Set<EmployeeDto>> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/get-employee/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }
}
