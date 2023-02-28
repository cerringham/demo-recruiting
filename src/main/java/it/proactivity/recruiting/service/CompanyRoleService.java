package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.CompanyRoleDtoBuilder;
import it.proactivity.recruiting.model.CompanyRole;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.repository.CompanyRoleRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.apache.commons.lang3.StringUtils;
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

    public ResponseEntity<List<CompanyRoleDto>> getAll() {
        List<CompanyRole> companyRoleList = companyRoleRepository.findByIsActive(true);

        List<CompanyRoleDto> dtoList = companyRoleList.stream()
                .map(c -> createCompanyRoleDto(c.getName(), c.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CompanyRoleDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<CompanyRole> companyRole = companyRoleRepository.findByIdAndIsActive(id, true);

        if (companyRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createCompanyRoleDto(companyRole.get().getName(), companyRole.get().getIsActive()));
    }

    private CompanyRoleDto createCompanyRoleDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("the parameters for creating the company role dto can'y be null or empty");
        }

        return CompanyRoleDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }
}
