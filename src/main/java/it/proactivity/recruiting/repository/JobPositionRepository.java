package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
    List<JobPosition> findByIsActive(Boolean isActive);
    Optional<JobPosition> findByIdAndIsActive(Long id, Boolean isActive);
}
