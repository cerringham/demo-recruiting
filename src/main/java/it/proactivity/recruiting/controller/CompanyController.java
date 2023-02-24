package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping(value = "get-all-companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return companyService.getAllCompanies();
    }

}
