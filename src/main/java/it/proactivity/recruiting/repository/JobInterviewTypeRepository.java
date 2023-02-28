package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobInterviewType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobInterviewTypeRepository extends JpaRepository<JobInterviewType, Long> {
    List<JobInterviewType> findByIsActive(Boolean isActive);
    Optional<JobInterviewType> findByIdAndIsActive(Long id, Boolean isActive);
}
