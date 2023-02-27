package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Expertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertiseRepository extends JpaRepository<Expertise, Long> {
    List<Expertise> findByIsActive(boolean isActive);

    Optional<Expertise> findByIdAndIsActive(Long id, boolean isActive);

    Optional<Expertise> findByName(String expertise);

    Optional<Expertise> findByNameIgnoreCase(String expertise);
}
