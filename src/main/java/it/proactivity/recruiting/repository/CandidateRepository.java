package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByIsActive(Boolean isActive);
    Optional<Candidate> findByIdAndIsActive(Long id, Boolean isActive);
    Optional<Candidate> findByFiscalCode(String fiscalCode);
    Optional<Candidate> findByEmail(String email);
    Optional<Candidate> findByPhoneNumber(String phoneNumber);
}
