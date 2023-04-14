package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
    List<JobPosition> findByIsActive(boolean isActive);

    Optional<JobPosition> findByIdAndIsActive(Long id, boolean isActive);
    @Transactional
    @Modifying
    @Query("UPDATE JobPosition j SET j.isActive = false WHERE j.id = ?1")
    int inactivateJobPositionById(Long id);

    @Query("SELECT jp FROM JobPosition jp INNER JOIN FETCH jp.jobInterviewList ORDER BY SIZE(jp.jobInterviewList) DESC")
    List<JobPosition> findJobPositionsOrderByJobInterviewListSizeDesc();

    /*
    @Query("SELECT j.jobPosition  FROM JobInterview j GROUP BY j.jobPosition, total ORDER BY COUNT(j.id) DESC")
    List<Object[]> findJobPositionWithMostInterviews();

     */
}
