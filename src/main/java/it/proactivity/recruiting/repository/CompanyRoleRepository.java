package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.CompanyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRoleRepository extends JpaRepository<CompanyRole, Long> {
    List<CompanyRole> findByIsActive(Boolean isActive);
    Optional<CompanyRole> findByIdAndIsActive(Long id, Boolean isActive);
}
