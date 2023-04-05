package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsernamePasswordAndIsActive(String username, String password, Boolean isActive);

    Optional<Account> findByUsername(String username);
}