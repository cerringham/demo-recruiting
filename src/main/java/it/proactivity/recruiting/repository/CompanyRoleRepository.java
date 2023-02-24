package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.CompanyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRoleRepository extends JpaRepository<CompanyRole, Long> {
}
