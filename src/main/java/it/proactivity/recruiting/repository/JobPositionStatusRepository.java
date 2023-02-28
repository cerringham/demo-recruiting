package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobPositionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPositionStatusRepository extends JpaRepository<JobPositionStatus, Long> {
    List<JobPositionStatus> findByIsActive(Boolean isActive);
    Optional<JobPositionStatus> findByIdAndIsActive(Long id, Boolean isActive);
}
