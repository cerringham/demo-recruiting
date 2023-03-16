package it.proactivity.recruiting.controller;

import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.service.CompanyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CompanyRoleDto> findById(@PathVariable Long id) {
        return companyRoleService.findById(id);
    }

    @PostMapping("/insert-company-role")
    public ResponseEntity insertCompanyRole(@RequestBody CompanyRoleDto companyRoleDto) {
        return companyRoleService.insertCompanyRole(companyRoleDto);
    }

    @PostMapping("/update-company-role")
    public ResponseEntity updateCompanyRole(@RequestBody CompanyRoleDto companyRoleDto) {
        return companyRoleService.updateCompanyRole(companyRoleDto);
    }

    @PostMapping("/delete-company-role")
    public ResponseEntity deleteCompanyRole(@RequestParam Long id) {
        return companyRoleService.deleteCompanyRole(id);
    }
}
