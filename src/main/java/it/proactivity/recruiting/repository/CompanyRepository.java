package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByIsActive(Boolean isActive);
    Optional<Company> findByIdAndIsActive(Long id, Boolean isActive);
}
