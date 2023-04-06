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
    public ResponseEntity<Set<EmployeeDto>> getAll(@RequestHeader("Token") String accessToken) {
        return employeeService.getAll(accessToken);
    }

    @GetMapping("/get-employee/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id, @RequestHeader("Token") String accessToken) {
        return employeeService.findById(id, accessToken);
    }

    @PostMapping("/insert-employee")
    public ResponseEntity insertEmployee(@RequestBody EmployeeDto dto, @RequestHeader("Token") String accessToken) {
        return employeeService.insertEmployee(dto, accessToken);
    }

    @GetMapping("/delete-employee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id, @RequestHeader("Token") String accessToken) {
        return employeeService.deleteEmployee(id, accessToken);
    }

    @PostMapping("/update-employee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto dto, @RequestHeader("Token") String accessToken) {
        return employeeService.updateEmployee(dto, accessToken);
    }
}
