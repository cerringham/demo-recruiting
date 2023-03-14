package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Candidate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByIsActive(boolean isActive);

    Optional<Candidate> findByIdAndIsActive(Long id, boolean isActive);

    @Modifying
    @Transactional
    @Query("UPDATE Candidate c SET c.isActive = false WHERE c.id = ?1")
    void deleteCandidate(Long id);

}
