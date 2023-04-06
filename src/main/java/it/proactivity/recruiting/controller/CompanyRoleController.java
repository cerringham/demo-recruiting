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
    public ResponseEntity<List<CompanyRoleDto>> getAll(@RequestHeader("Token") String accessToken) {
        return companyRoleService.getAll(accessToken);
    }

    @GetMapping("/get-companyRole/{id}")
    public ResponseEntity<CompanyRoleDto> findById(@PathVariable Long id, @RequestHeader("Token") String accessToken) {
        return companyRoleService.findById(id, accessToken);
    }

    @PostMapping("/insert-company-role")
    public ResponseEntity insertCompanyRole(@RequestBody CompanyRoleDto companyRoleDto, @RequestHeader("Token") String accessToken) {
        return companyRoleService.insertCompanyRole(companyRoleDto, accessToken);
    }

    @PostMapping("/update-company-role")
    public ResponseEntity updateCompanyRole(@RequestBody CompanyRoleDto companyRoleDto, @RequestHeader("Token") String accessToken) {
        return companyRoleService.updateCompanyRole(companyRoleDto, accessToken);
    }

    @PostMapping("/delete-company-role")
    public ResponseEntity deleteCompanyRole(@RequestParam Long id, @RequestHeader("Token") String accessToken) {
        return companyRoleService.deleteCompanyRole(id, accessToken);
    }
}
