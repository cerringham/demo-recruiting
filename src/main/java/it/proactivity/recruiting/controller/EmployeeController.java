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
    public ResponseEntity<EmployeeDto> findById(@RequestHeader("Token") String accessToken ,@PathVariable Long id) {
        return employeeService.findById(accessToken, id);
    }

    @PostMapping("/insert-employee")
    public ResponseEntity insertEmployee(@RequestHeader("Token") String accessToken ,@RequestBody EmployeeDto dto) {
        return employeeService.insertEmployee(accessToken, dto);
    }

    @GetMapping("/delete-employee/{id}")
    public ResponseEntity deleteEmployee(@RequestHeader("Token") String accessToken ,@PathVariable Long id) {
        return employeeService.deleteEmployee(accessToken, id);
    }

    @PostMapping("/update-employee")
    public ResponseEntity updateEmployee(@RequestHeader("Token") String accessToken ,@RequestBody EmployeeDto dto) {
        return employeeService.updateEmployee(accessToken, dto);
    }
}
