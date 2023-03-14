package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.CompanyRole;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.repository.CompanyRoleRepository;
import it.proactivity.recruiting.utility.CompanyRoleUtility;
import it.proactivity.recruiting.utility.CompanyRoleValidator;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyRoleService {

    @Autowired
    CompanyRoleRepository companyRoleRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    CompanyRoleUtility companyRoleUtility;

    @Autowired
    CompanyRoleValidator companyRoleValidator;

    public ResponseEntity<List<CompanyRoleDto>> getAll() {
        List<CompanyRole> companyRoleList = companyRoleRepository.findByIsActive(true);

        List<CompanyRoleDto> dtoList = companyRoleList.stream()
                .map(c -> companyRoleUtility.createCompanyRoleDto(c.getName(), c.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CompanyRoleDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<CompanyRole> companyRole = companyRoleRepository.findByIdAndIsActive(id, true);

        if (companyRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(companyRoleUtility.createCompanyRoleDto(companyRole.get().getName(),
                companyRole.get().getIsActive()));
    }

    public ResponseEntity insertCompanyRole(String name) {
        if (!companyRoleValidator.validateName(name)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            companyRoleUtility.checkDefaultCompanyRoleExistence(name);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FOUND).body(e.getMessage());
        }
        CompanyRole companyRole = companyRoleUtility.createCompanyRole(name);
        companyRoleRepository.save(companyRole);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity deleteCompanyRole(Long id) {
        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<CompanyRole> companyRole = companyRoleRepository.findById(id);
        if (companyRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            companyRoleUtility.checkDefaultCompanyRoleExistence(companyRole.get().getName());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        companyRole.get().setIsActive(false);
        companyRoleRepository.save(companyRole.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity updateCompanyRole(Long id, String name) {
        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!companyRoleValidator.validateName(name)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<CompanyRole> companyRole = companyRoleRepository.findById(id);

        if (companyRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            if (companyRoleUtility.checkDefaultCompanyRoleExistence(name) &&
                    companyRoleUtility.checkDefaultCompanyRoleExistence(companyRole.get().getName())) {

                companyRole.get().setName(WordUtils.capitalizeFully(name));
                companyRoleRepository.save(companyRole.get());
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
