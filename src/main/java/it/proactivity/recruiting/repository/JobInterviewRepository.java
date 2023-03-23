package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobInterviewRepository extends JpaRepository<JobInterview, Long> {
    List<JobInterview> findByIsActive(boolean isActive);

    Optional<JobInterview> findByIdAndIsActive(Long id, boolean isActive);

    List<JobInterview> findByCandidateId(Long id);
}
