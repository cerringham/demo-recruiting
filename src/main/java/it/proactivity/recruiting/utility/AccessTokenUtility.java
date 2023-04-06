package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Component
public class AccessTokenUtility {

    @Autowired
    AccessTokenRepository accessTokenRepository;

    private static final Set<String> ROLE_NAME_SET = Set.of("admin", "hr", "dev");


    public Boolean isExpired(AccessToken accessToken) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = accessToken.getCreationTokenDateTime().plusMinutes(10);
        return now.isAfter(expirationTime);
    }

    public Boolean verifyAccountCredential(String token, Set<Predicate<String>> predicateSet) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        Set<String> authorizedSet = createAuthorizedRoleNameSet(predicateSet);

        Optional<String> validToken = accessTokenRepository.findRoleNameByTokenValue(token, authorizedSet);
        return validToken.isPresent();
    }

    public Set<Predicate<String>> createPredicateSet(List<String> roleNameAuthorizedList) {
        Set<Predicate<String>> predicateSet = new HashSet<>();
        roleNameAuthorizedList
                .forEach(r -> {
                    Predicate<String> authorizedPredicate = s -> s.equals(r);
                    predicateSet.add(authorizedPredicate);
                });
        return predicateSet;
    }

    private Set<String> createAuthorizedRoleNameSet(Set<Predicate<String>> predicateSet) {
        return predicateSet.stream()
                .flatMap(p -> ROLE_NAME_SET.stream()
                        .filter(p)
                ).collect(Collectors.toSet());
    }
}
