package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.EmployeeDto;
import it.proactivity.recruiting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping("/insert-employee")
    public ResponseEntity insertEmployee(@RequestBody EmployeeDto dto) {
        return employeeService.insertEmployee(dto);
    }

    @GetMapping("/delete-employee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @PostMapping("/update-employee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto dto) {
        return employeeService.updateEmployee(dto);
    }
}
