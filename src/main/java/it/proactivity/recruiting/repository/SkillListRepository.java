package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.SkillList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillListRepository extends JpaRepository<SkillList, Long> {
}
