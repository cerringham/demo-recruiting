package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.CompanyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRoleRepository extends JpaRepository<CompanyRole, Long> {
    List<CompanyRole> findByIsActive(boolean isActive);

    Optional<CompanyRole> findByIdAndIsActive(Long id, boolean isActive);

    Optional<CompanyRole> findByNameIgnoreCaseAndIsActive(String companyRoleName, boolean b);

    Optional<CompanyRole> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE CompanyRole c SET c.isActive = false WHERE c.id = ?1")
    int inactivateCompanyRoleById(Long id);
}
