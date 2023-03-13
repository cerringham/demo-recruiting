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

    @Autowired
    PredicateUtility predicateUtility;

    public Map<Skill, Level> insertNewSkillsAndReturnSkillLevelMap(Map<String, Level> dtoMap) {
        Map<String, Level> dtoCapitalizeMap = new HashMap<>();
        for (Map.Entry<String, Level> entry : dtoMap.entrySet()) {
            dtoCapitalizeMap.put(WordUtils.capitalizeFully(entry.getKey()), entry.getValue());
        }
        Map<Skill, Level> skillLevelMap = new HashMap<>();

        Set<String> dtoSkills = dtoMap.keySet();

        Set<Skill> skills = skillRepository.findByNameIgnoreCaseIn(dtoSkills);
        //salvataggio delle skill non presenti nel db
        if (skills.isEmpty()) {
            dtoSkills.forEach(s -> {
                Skill skill = SkillBuilder.newBuilder(WordUtils.capitalizeFully(s)).isActive(true).build();
                skillLevelMap.put(skill, dtoCapitalizeMap.get(s));
                skillRepository.save(skill);
            });
        }

        //controllo eventuale che nella mappa ci siano skill esistenti e non
        if ((!skills.isEmpty()) && skills.size() != dtoSkills.size()) {

            Set<String> nonExistentSkillsName = dtoSkills.stream()
                    .filter(s -> predicateUtility.filterSkillsName(skills, s))
                    .collect(Collectors.toSet());

            skills.forEach(s -> skillLevelMap.put(s, dtoCapitalizeMap.get(s.getName())));

            nonExistentSkillsName.forEach(s -> {
                String correctSkillName = WordUtils.capitalizeFully(s);
                Skill skill = SkillBuilder.newBuilder(correctSkillName).isActive(true).build();
                skillLevelMap.put(skill, dtoCapitalizeMap.get(correctSkillName));
                skillRepository.save(skill);
            });
        }

        //Controllo che sulla mappa ci siano solo skill già esistenti
        if (skills.size() == dtoSkills.size()) {

            skills.stream()
                    .forEach(s -> {
                        skillLevelMap.put(s, dtoCapitalizeMap.get(s.getName()));
                    });
        }
        return skillLevelMap;
    }
}
