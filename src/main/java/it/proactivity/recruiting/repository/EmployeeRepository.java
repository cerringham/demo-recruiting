package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByIsActive(boolean isActive);

    Optional<Employee> findByIdAndIsActive(Long id, boolean isActive);

    @Query("SELECT COUNT(e.id) FROM Employee e WHERE e.companyRole.name = 'CEO'")
    Long countNumberOfCeo();

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.isActive = false WHERE e.id = ?1")
    void deleteEmployee(Long id);
}
