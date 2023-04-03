package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.Skill;
import org.springframework.stereotype.Component;



import java.util.Set;
import java.util.function.Predicate;


@Component
public class PredicateUtility {

    public final Predicate<String> FILTER_ADMIN = s -> s.equals("admin");

    public final Predicate<String> FILTER_HR = s -> s.equals("hr");

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
