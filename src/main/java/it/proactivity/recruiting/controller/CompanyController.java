package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/get-all-companies")
    public ResponseEntity<Set<CompanyDto>> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/get-company/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable Long id) {
        return companyService.findById(id);
    }
}
