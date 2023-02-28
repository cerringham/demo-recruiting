package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByIsActive(Boolean isActive);
    Optional<Skill> findByIdAndIsActive(Long id, Boolean isActive);
}
