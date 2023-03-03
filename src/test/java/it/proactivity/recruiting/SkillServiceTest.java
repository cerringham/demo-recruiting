package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.SkillRepository;
import it.proactivity.recruiting.service.SkillService;
import it.proactivity.recruiting.utility.SkillValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class SkillServiceTest {

    @Autowired
    SkillService skillService;

    @Autowired
    SkillValidator skillValidator;

    @Autowired
    SkillRepository skillRepository;

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
    void createSkillListTest() {
        List<String> stringList = Arrays.asList("Java", "Php", "Altro");
        List<Skill> skills = skillValidator.createSkillList(stringList);
        assertTrue(skills.size() == 3);
        assertNotNull(skills);
        List<String> stringList2 = Arrays.asList("Java", "Php", "Altro oi");
        List<Skill> skills2 = skillValidator.createSkillList(stringList2);
        assertFalse(skills2.size() == 3);
    }
}
