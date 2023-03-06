package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.SkillRepository;
import it.proactivity.recruiting.service.SkillService;
import org.aspectj.apache.bcel.classfile.Module;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class SkillServiceTest {

    @Autowired
    SkillService skillService;
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
    void insertSkillPositiveTest() {
        SkillDto dto = new SkillDto("laravel", true);
        Long numberOfSkillBeforeInsert = skillRepository.findByIsActive(true).stream().count();

        skillService.insertSkill(dto);
        Long numberOfSkillAfterInsert = skillRepository.findByIsActive(true).stream().count();

        assertTrue(numberOfSkillBeforeInsert < numberOfSkillAfterInsert);
    }

    @Test
    void insertExistingSkillNegativeTest() {
        SkillDto dto = new SkillDto("laravel", true);

        ResponseEntity response = ResponseEntity.status(HttpStatus.FOUND).build();
        ResponseEntity result = skillService.insertSkill(dto);

        assertEquals(response.getStatusCode(), result.getStatusCode());
    }

    @Test
    void deleteSkillPositiveTest() {
        skillService.deleteSkill(2l);
        Optional<Skill> skill = skillRepository.findById(2l);
        assertTrue(!skill.get().getIsActive());
    }

    @Test
    void updateSkillPositiveTest() {
        SkillDto dto = new SkillDto(6l, "html5");
        skillService.updateSkill(dto);
        Optional<Skill> skill = skillRepository.findById(6l);
        assertTrue(skill.get().getName().equals("Html5"));
    }
}
