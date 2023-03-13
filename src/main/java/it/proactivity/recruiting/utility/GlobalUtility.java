package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.SkillBuilder;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.SkillRepository;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GlobalUtility {

    @Autowired
    SkillRepository skillRepository;

    public Map<Skill, Level> insertNewSkillsAndReturnSkillLevelMap(Map<String, Level> dtoMap) {
        Map<String, Level> dtoCapitalizeMap = new HashMap<>();

        dtoMap.forEach((key, value) -> dtoCapitalizeMap.put(WordUtils.capitalizeFully(key), value));

        Map<Skill, Level> skillLevelMap = new HashMap<>();

        Set<String> dtoSkills = dtoCapitalizeMap.keySet();

        Set<String> skills = skillRepository.findByNameIn(dtoSkills).stream().map(Skill::getName).collect(Collectors.toSet());

        if (skills.isEmpty()) {
            dtoSkills.forEach(s -> {
                Skill skill = SkillBuilder.newBuilder(s).isActive(true).build();
                skillLevelMap.put(skill, dtoCapitalizeMap.get(s));
                skillRepository.save(skill);
            });
        } else {
            dtoSkills.forEach(s -> {
                if (!skills.contains(s)) {
                    Skill skill = SkillBuilder.newBuilder(s).isActive(true).build();
                    skillLevelMap.put(skill, dtoCapitalizeMap.get(skill.getName()));
                    skillRepository.save(skill);
                } else {
                    Skill skill = skillRepository.findByNameAndIsActive(s, true).get();
                    skillLevelMap.put(skill, dtoCapitalizeMap.get(s));
                }
            });
        }
        return skillLevelMap;
    }
}
