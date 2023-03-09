package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.Skill;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PredicateUtility {

    public Boolean filterSkillsName(Set<Skill> skills, String name) {
        boolean result = true;

        for (Skill s : skills) {
            if (s.getName().equalsIgnoreCase(name)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
