package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/get-all-companies")
    public ResponseEntity<List<CompanyDto>> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/get-company/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PostMapping("/delete-company")
    public ResponseEntity<CompanyDto> deleteById(@RequestParam Long id) {
        return companyService.deleteById(id);
    }
}
