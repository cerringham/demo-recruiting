package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByIsActive(boolean isActive);

    Optional<Employee> findByIdAndIsActive(Long id, boolean isActive);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.companyRole = 1")
    Long countNumberOfCeo();
}
