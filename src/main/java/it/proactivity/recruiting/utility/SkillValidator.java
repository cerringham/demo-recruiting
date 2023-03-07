package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.SkillRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//validazione delle skill
//	- nome della skill: il nome della skill rispetta il formato indicato? Java, Database Oracle ok
//	- preseza della skill sul db:
//		se non esistono le creo e le recupero --> List<Skill> skillListForCandidate ok
//		se esistono recupero tutte le skill --> List<Skill> skillListForCandidate ok
//Candidate
//	- creo una istanza di Candidate newCandidate
//Skill
//	- sono ok
//CV
//	- creo n oggetti (dove n è il numero delle skills) di tipo Curriculum dove setto come candidate l'oggetto appena creato (newCandidate)
//	- associo questa lista di oggetti all'attriburo candidateSkillList di newCandidate
//	- session.save() di newCandidate salva anche i cv
import java.util.*;

@Component
public class SkillValidator {

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SkillUtility skillUtility;

    public Boolean validateSkillName(String skillName) {
        if (StringUtils.isEmpty(skillName)) {
            return false;
        }
        skillName = WordUtils.capitalizeFully(skillName);
        String[] array = skillName.split(" ");
        for (String s : array) {
            if (!Character.isUpperCase(s.charAt(0))) {
                return false;
            }
        }
        return true;
    }

    public Set<Skill> validateSkillSet(Set<SkillDto> skills) {
        if (skills.isEmpty()) {
            return null;
        }
        Set<Skill> skillSet = new HashSet<>();
        for (SkillDto  s : skills) {
            Optional<Skill> skill = skillRepository.findByNameIgnoreCaseAndIsActive(s.getName(), true);
            if (skill.isPresent()) {
                skillSet.add(skill.get());

            } else if (validateSkillName(s.getName())) {
                    Skill newSkill = new Skill(s.getName(), true);
                    skillRepository.save(newSkill);
                    skillSet.add(newSkill);
            }
        }
        return skillSet;
    }
}
