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
    public ResponseEntity<CompanyRoleDto> findById(@RequestHeader("Token") String accessToken, @PathVariable Long id) {
        return companyRoleService.findById(accessToken, id);
    }

    @PostMapping("/insert-company-role")
    public ResponseEntity insertCompanyRole(@RequestHeader("Token") String accessToken, @RequestBody CompanyRoleDto companyRoleDto) {
        return companyRoleService.insertCompanyRole(accessToken, companyRoleDto);
    }

    @PostMapping("/update-company-role")
    public ResponseEntity updateCompanyRole(@RequestHeader("Token") String accessToken, @RequestBody CompanyRoleDto companyRoleDto) {
        return companyRoleService.updateCompanyRole(accessToken, companyRoleDto);
    }

    @PostMapping("/delete-company-role")
    public ResponseEntity deleteCompanyRole(@RequestHeader("Token") String accessToken, @RequestParam Long id) {
        return companyRoleService.deleteCompanyRole(accessToken, id);
    }
}
