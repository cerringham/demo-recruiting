package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    List<Curriculum> findByIsActive(Boolean isActive);
    Optional<Curriculum> findByIdAndIsActive(Long id, Boolean isActive);
}
