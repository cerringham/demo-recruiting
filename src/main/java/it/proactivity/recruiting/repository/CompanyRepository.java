package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByIsActive(boolean isActive);

    Optional<Company> findByIdAndIsActive(Long id, boolean isActive);

    Optional<Company> findByNameIgnoreCaseAndIsActive(String companyName, boolean b);

    @Query("SELECT c.cooId FROM Company c WHERE c.id = ?1")
    Long checkIfCooExists(Long companyId);


    @Query(nativeQuery = true, value = "SELECT c.id FROM company c WHERE c.name = ?1 AND c.is_active IS TRUE")
    Optional<Long> findByName(String companyName);

}
