package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobInterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobInterviewStatusRepository extends JpaRepository<JobInterviewStatus, Long> {
}
