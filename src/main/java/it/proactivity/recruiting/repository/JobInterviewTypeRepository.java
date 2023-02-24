package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobInterviewType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobInterviewTypeRepository extends JpaRepository<JobInterviewType, Long> {
}
