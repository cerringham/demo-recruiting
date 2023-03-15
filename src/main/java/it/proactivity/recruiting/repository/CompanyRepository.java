package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByIsActive(boolean isActive);

    Optional<Company> findByIdAndIsActive(Long id, boolean isActive);

    Optional<Company> findByNameIgnoreCaseAndIsActive(String companyName, boolean b);

    @Query("SELECT c.cooId FROM Company c WHERE c.id = ?1")
    Long checkIfCooExists(Long companyId);

    Optional<Company> findByName(String companyName);

    Optional<Company> findByNameAndIsActive(String capitalizeFully, boolean b);

    List<Company> findByIsDefault(boolean b);
}
