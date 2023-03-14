package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Skill;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Modifying
    @Transactional
    @Query("UPDATE Skill s SET s.isActive = false WHERE s.id = ?1")
    void deleteSkill(Long id);
}
