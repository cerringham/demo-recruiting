package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.SkillLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillLevelRepository extends JpaRepository<SkillLevel, Long> {
    List<SkillLevel> findByIsActive(boolean isActive);

    Optional<SkillLevel> findByIdAndIsActive(Long id, boolean isActive);
}
