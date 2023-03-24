package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobInterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobInterviewStatusRepository extends JpaRepository<JobInterviewStatus, Long> {
    List<JobInterviewStatus> findByIsActive(boolean isActive);

    Optional<JobInterviewStatus> findByIdAndIsActive(Long id, boolean isActive);

    Optional<JobInterviewStatus> findByName(String name);

    Optional<JobInterviewStatus> findBySequenceOrder(Integer sequenceOrder);

    @Query("SELECT j FROM JobInterviewStatus j WHERE j.sequenceOrder =")
    Optional<JobInterviewStatus> findByMaxSequenceOrder();
}
