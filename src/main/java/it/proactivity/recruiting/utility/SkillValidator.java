package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.repository.SkillRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SkillValidator {

    @Autowired
    SkillRepository skillRepository;

    public Boolean validateSkillName(String skillName) {
        if (StringUtils.isEmpty(skillName)) {
            return false;
        }
        Optional<Skill> skill = skillRepository.findByNameIgnoreCaseAndIsActive(skillName, true);
        if (skill.isPresent()) {
            return true;
        } else {
            Skill newSkill = new Skill(skillName, true);
            skillRepository.save(newSkill);
            return true;
        }
    }
}
