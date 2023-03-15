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
        if (!companyRoleUtility.validParameters(companyRoleDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<CompanyRole> companyRole = companyRoleRepository.findByName(companyRoleDto.getName());
        if (companyRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            CompanyRole newCompanyRole = CompanyRoleBuilder.newBuilder(companyRoleDto.getName())
                    .isActive(true)
                    .build();
            companyRoleRepository.save(newCompanyRole);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}
