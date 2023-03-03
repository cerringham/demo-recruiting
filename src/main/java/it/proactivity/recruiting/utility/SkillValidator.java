package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.Skill;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SkillValidator {

    @Autowired
    SkillRepository skillRepository;

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

    public List<Skill> createSkillList(List<String> skillNames) {
        if (skillNames == null) {
            return null;
        }
        List<Skill> skills = new ArrayList<>();
        for (String s : skillNames) {
            Optional<Skill> skill = skillRepository.findByNameIgnoreCaseAndIsActive(s, true);
            if (skill.isPresent()) {
                skills.add(skill.get());

            } else if (validateSkillName(s)) {
                Skill newSkill = new Skill(s, true);
                skillRepository.save(newSkill);
                skills.add(newSkill);
            }
        }
        return skills;
    }
}
