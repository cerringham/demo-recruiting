package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/get-all-companies")
    public ResponseEntity<List<CompanyDto>> getAll(@RequestHeader("Token") String accessToken) {
        return companyService.getAll(accessToken);
    }

    @GetMapping("/get-company/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable Long id, @RequestHeader("Token") String accessToken) {
        return companyService.findById(id, accessToken);
    }

    @GetMapping("/check-company-presence")
    public ResponseEntity checkCompanyPresence(@RequestHeader("Token") String accessToken) {
        return companyService.checkCompanyPresence(accessToken);
    }

}
