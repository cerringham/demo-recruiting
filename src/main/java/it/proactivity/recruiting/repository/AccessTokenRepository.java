package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

    Optional<AccessToken> findFirstByAccountOrderByIdDesc(Account account);
}
