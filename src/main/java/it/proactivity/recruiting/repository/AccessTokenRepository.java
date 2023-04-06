package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
    Optional<AccessToken> findByNameAndIsActive(String name, Boolean isActive);

    Optional<AccessToken> findByName(String name);
}
