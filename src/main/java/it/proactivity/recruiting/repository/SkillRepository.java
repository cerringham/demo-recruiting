package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByIsActive(boolean isActive);

    Optional<Skill> findByIdAndIsActive(Long id, boolean isActive);

    Optional<Skill> findByNameIgnoreCase(String capitalizeFully);

    Set<Skill> findByNameIn(Set<String> dtoSkills);

    Optional<Skill> findByNameAndIsActive(String s, Boolean isActive);
}
