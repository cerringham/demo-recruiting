package it.proactivity.recruiting.utility;


import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CandidateValidator {

    public boolean validateSkill(Set<String> skills) {
        return skills != null && !skills.isEmpty();
    }

}
