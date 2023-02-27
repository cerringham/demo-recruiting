package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByIsActive(boolean isActive);


    Optional<Candidate> findByIdAndIsActive(Long id, boolean isActive);

    @Query("SELECT c FROM candidate c INNER JOIN FETCH c.candidateSkillList WHERE ")
    Optional<Candidate> findByIdAndIsActiveWithCurriculumList(Long id, boolean b);
}
