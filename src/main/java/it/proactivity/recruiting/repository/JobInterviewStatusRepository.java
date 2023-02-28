package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobInterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobInterviewStatusRepository extends JpaRepository<JobInterviewStatus, Long> {
    List<JobInterviewStatus> findByIsActive(Boolean isActive);
    Optional<JobInterviewStatus> findByIdAndIsActive(Long id, Boolean isActive);
}
