package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.SkillLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillLevelRepository extends JpaRepository<SkillLevel, Long> {
}
