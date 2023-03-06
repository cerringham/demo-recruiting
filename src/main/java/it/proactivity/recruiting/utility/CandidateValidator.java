package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.myEnum.Level;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class CandidateValidator {

    public Boolean validateSkill(Set<String> skills) {
        if (skills == null || skills.isEmpty()) {
            return false;
        }
        return true;
    }

}
