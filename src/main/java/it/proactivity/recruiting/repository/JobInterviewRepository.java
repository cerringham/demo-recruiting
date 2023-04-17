package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Candidate;
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

    Optional<List<JobInterview>> findByCandidateId(Long id);

    Optional<JobInterview> findFirstByCandidateOrderByIdDesc(Candidate candidate);

    @Modifying
    @Transactional
    @Query("UPDATE  JobInterview j SET j.isActive = false WHERE j.id = ?1")
    void deleteJobInterview(Long id);

    @Query("SELECT count(j) FROM JobInterview j WHERE j.jobInterviewStatus.name = ?1")
    Optional<Integer> findByJobInterviewStatusName(String name);
}
