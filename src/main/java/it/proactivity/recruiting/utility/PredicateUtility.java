package it.proactivity.recruiting.utility;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PredicateUtility {

    public Boolean filterSkillsName(Set<String> names, String name) {
        Boolean result = true;

        for (String n : names) {
            if (n.equalsIgnoreCase(name)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
