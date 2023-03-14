package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobPosition;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
    List<JobPosition> findByIsActive(boolean isActive);

    Optional<JobPosition> findByIdAndIsActive(Long id, boolean isActive);

    @Modifying
    @Transactional
    @Query("UPDATE JobPosition j SET j.isActive = false WHERE j.id = ?1")
    void deleteJobPosition(Long id);
}
