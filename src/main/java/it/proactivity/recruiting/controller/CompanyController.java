package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/find-all-companies")
    public ResponseEntity<List<CompanyDto>> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/find-company/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable Long id) {
        return companyService.findById(id);
    }
}
