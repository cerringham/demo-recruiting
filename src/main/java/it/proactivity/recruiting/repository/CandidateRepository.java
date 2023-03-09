package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByIsActive(boolean isActive);

    Optional<Candidate> findByIdAndIsActive(Long id, boolean isActive);

}
