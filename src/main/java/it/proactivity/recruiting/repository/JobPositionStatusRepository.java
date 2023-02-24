package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobPositionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionStatusRepository extends JpaRepository<JobPositionStatus, Long> {
}
