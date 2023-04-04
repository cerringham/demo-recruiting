package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r.name FROM Role r")
    List<String> findAllNames();
}
