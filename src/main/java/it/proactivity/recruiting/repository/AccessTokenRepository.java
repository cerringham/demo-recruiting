package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

    Optional<AccessToken> findFirstByAccountOrderByIdDesc(Account account);

    Optional<AccessToken> findByValue(String value);

    @Query("SELECT a.account.role.name FROM AccessToken a WHERE a.value = ?1 AND a.isActive = true AND a.account.role.name IN ('admin', 'hr')")
    Optional<String> findRoleNameByTokenValueForAdminAndHr(String tokenValue);

    @Query("SELECT a.value FROM AccessToken a JOIN a.account acc WHERE acc.username = ?1 AND a.isActive = true ORDER BY a.creationTokenDateTime DESC")
    Optional<String> findLatestTokenValueByUsername(String username);


}
