package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobInterviewRepository extends JpaRepository<JobInterview, Long> {
}
