package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.JobInterviewType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobInterviewTypeRepository extends JpaRepository<JobInterviewType, Long> {
    List<JobInterviewType> findByIsActive(boolean isActive);

    Optional<JobInterviewType> findByIdAndIsActive(Long id, boolean isActive);

    @Query("SELECT j FROM JobInterviewType j WHERE j.name = 'Behavioral'")
    JobInterviewType findBehavioral();

    @Query("SELECT j FROM JobInterviewType j WHERE j.name = 'Technical'")
    JobInterviewType findTechnical();

    @Query("SELECT j FROM JobInterviewType j WHERE j.name = 'Contract proposal'")
    JobInterviewType findContractProposal();
}
