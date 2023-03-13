package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.myEnum.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class JobPositionValidator {

    @Value("${recruiting.minimumSkillsForJobPosition}")
    private int minimumSkillsForJobPosition;

    public Boolean validateSkillLevelMap(Map<String, Level> skillLevelMap) {
        if (skillLevelMap == null) {
            return false;
        }
        Set<String> skillNames = skillLevelMap.keySet();
        List<Level> levels = new ArrayList<>(skillLevelMap.values());

        if (skillNames.size() < minimumSkillsForJobPosition) {
            return false;
        }

        if (levels.contains(null)) {
            return false;
        }
        return true;
    }
}
