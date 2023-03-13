package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobPositionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPositionStatusRepository extends JpaRepository<JobPositionStatus, Long> {
    List<JobPositionStatus> findByIsActive(boolean isActive);

    Optional<JobPositionStatus> findByIdAndIsActive(Long id, boolean isActive);

    Optional<JobPositionStatus> findByNameAndIsActive(String aNew, boolean b);
}
