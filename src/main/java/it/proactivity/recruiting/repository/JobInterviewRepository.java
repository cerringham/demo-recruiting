package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobInterviewRepository extends JpaRepository<JobInterview, Long> {
    List<JobInterview> findByIsActive(boolean isActive);

    Optional<JobInterview> findByIdAndIsActive(Long id, boolean isActive);

    Optional<JobInterview> findById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE JobInterview j SET j.isActive = false WHERE j.id = ?1")
    int inactivateJobInterviewById(Long id);

    List<JobInterview> findByCandidateIdAndIsActive(Long id, boolean b);
}
