package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.CompanyRoleDtoBuilder;
import it.proactivity.recruiting.model.CompanyRole;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.model.dto.CompanyRoleDto;
import it.proactivity.recruiting.repository.CompanyRoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompanyRoleService {
    @Autowired
    CompanyRoleRepository companyRoleRepository;

    public ResponseEntity<Set<CompanyRoleDto>> getAll() {
        List<CompanyRole> companyList = companyRoleRepository.findAll();
        if (companyList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Set<CompanyRoleDto> dtoSet = companyList.stream()
                .map(c -> createCompanyRoleDto(c.getName(), c.getIsActive()))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(dtoSet);
    }
    public ResponseEntity<CompanyRoleDto> findById(Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<CompanyRole> companyRole = companyRoleRepository.findById(id);
        if (companyRole.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CompanyRoleDto companyRoleDto = createCompanyRoleDto(companyRole.get().getName(),
                companyRole.get().getIsActive());
        return ResponseEntity.ok(companyRoleDto);
    }

    public CompanyRoleDto createCompanyRoleDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException();
        }
        return CompanyRoleDtoBuilder.newBuilder(name)
                .isActive(isActive).build();
    }


}
