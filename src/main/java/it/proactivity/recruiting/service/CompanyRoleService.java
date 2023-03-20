package it.proactivity.recruiting.service;


import it.proactivity.recruiting.builder.CompanyRoleBuilder;
import it.proactivity.recruiting.model.CompanyRole;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.repository.CompanyRoleRepository;
import it.proactivity.recruiting.utility.CompanyRoleUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
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

    public ResponseEntity insertCompanyRole(CompanyRoleDto companyRoleDto) {
        String companyRoleName = companyRoleUtility.transformCompanyRoleName(companyRoleDto.getName());
        Optional<CompanyRole> companyRole = companyRoleRepository.findByName(companyRoleName);
        if (companyRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            CompanyRole newCompanyRole = CompanyRoleBuilder.newBuilder(companyRoleName)
                    .isActive(true)
                    .build();
            companyRoleRepository.save(newCompanyRole);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    public ResponseEntity updateCompanyRole(CompanyRoleDto companyRoleDto) {
        if (!globalValidator.validateId(companyRoleDto.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String companyRoleName = companyRoleUtility.transformCompanyRoleName(companyRoleDto.getName());
        Optional<CompanyRole> companyRole = companyRoleRepository.findById(companyRoleDto.getId());

        if (companyRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (companyRoleUtility.checkIfDefaultRole(companyRoleName) ||
                companyRoleUtility.checkIfDefaultRole(companyRole.get().getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        companyRole.get().setName(companyRoleName);
        companyRole.get().setIsActive(true);
        companyRoleRepository.save(companyRole.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity deleteCompanyRole(Long id) {
        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<CompanyRole> companyRole = companyRoleRepository.findById(id);
        if (companyRole.isPresent()) {
            if (companyRoleUtility.checkIfDefaultRole(companyRole.get().getName())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            int deleted = companyRoleRepository.inactivateCompanyRoleById(id);
            if (deleted == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
