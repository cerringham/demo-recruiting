package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.service.CompanyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyRoleController {

    @Autowired
    CompanyRoleService companyRoleService;

    @GetMapping("/get-all-companyRoles")
    public ResponseEntity<List<CompanyRoleDto>> getAll() {
        return companyRoleService.getAll();
    }

    @GetMapping("/get-companyRole/{id}")
    public ResponseEntity<CompanyRoleDto> getById(@PathVariable Long id) {
        return companyRoleService.getById(id);
    }
}
