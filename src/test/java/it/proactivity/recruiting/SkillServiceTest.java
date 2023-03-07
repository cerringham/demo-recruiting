package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.SkillRepository;
import it.proactivity.recruiting.service.SkillService;
import it.proactivity.recruiting.utility.SkillUtility;
import it.proactivity.recruiting.utility.SkillValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.Assert.*;

@SpringBootTest
public class SkillServiceTest {

    @Autowired
    SkillService skillService;

    @Autowired
    SkillValidator skillValidator;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SkillUtility skillUtility;

    @Test
    void getAllSkillsTest() {
        List<SkillDto> dtoList = skillService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getSkillByIdTest() {
        SkillDto dto = skillService.findById(1l).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void validateSkillNameTest() {
        String name = "Php";
        assertTrue(skillValidator.validateSkillName(name));
        assertNotNull(name);
        String name2= "Prova skill";
        assertTrue(skillValidator.validateSkillName(name2));
    }

    @Test
    void findByNameIgnoreCaseAndIsActiveTest() {
        Optional<Skill> skill = skillRepository.findByNameIgnoreCaseAndIsActive("java", true);
        assertTrue(skill.isPresent());
        assertNotNull(skill);
        Optional<Skill> skill2 = skillRepository.findByNameIgnoreCaseAndIsActive("altro", true);
        assertFalse(skill2.isPresent());
    }

    @Test
    void createSkillSetTest() {
        Set<SkillDto> s = new HashSet<>();
        Optional<Skill> skill = skillRepository.findByNameIgnoreCaseAndIsActive("C+", true);
        SkillDto skillDto = skillUtility.createSkillDto(skill.get());
        s.add(skillDto);
        Set<Skill> skills = skillValidator.validateSkillSet(s);
        assertTrue(skills.size() == 1);
        assertNotNull(skills);
    }


}
