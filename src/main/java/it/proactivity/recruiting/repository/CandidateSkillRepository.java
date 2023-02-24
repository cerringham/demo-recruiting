package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
}
