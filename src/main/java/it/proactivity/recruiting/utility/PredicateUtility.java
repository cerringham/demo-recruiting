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

    public Boolean validateHrRoleName(String hrRole) {
        return hrRole.equals("hr");
    }

    public Boolean validateAdminRoleName(String adminRole) {
        return adminRole.equals("admin");
    }

    public Boolean validateDevRoleName(String devRole) {
       return devRole.equals("dev");
    }
}
