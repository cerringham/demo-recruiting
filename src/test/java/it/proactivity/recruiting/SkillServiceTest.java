package it.proactivity.recruiting;

import it.proactivity.recruiting.mapper.SkillMapper;
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
 class SkillServiceTest {

    @Autowired
    SkillService skillService;
    @Autowired
    SkillRepository skillRepository;

    @Test
    void getAllSkillsTest() {
        List<SkillDto> dtoList = skillService.getAll("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010").getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getSkillByIdTest() {
        SkillDto dto = skillService.findById("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", 1L).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void insertSkillPositiveTest() {
        SkillDto dto = new SkillDto("laravel", true);
        long numberOfSkillBeforeInsert = skillRepository.findByIsActive(true).size();

        skillService.insertSkill("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", dto);
        long numberOfSkillAfterInsert = skillRepository.findByIsActive(true).size();

        assertTrue(numberOfSkillBeforeInsert < numberOfSkillAfterInsert);
    }

    @Test
    void insertExistingSkillNegativeTest() {
        SkillDto dto = new SkillDto("laravel", true);

        ResponseEntity response = ResponseEntity.status(HttpStatus.FOUND).build();
        ResponseEntity result = skillService.insertSkill("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", dto);

        assertEquals(response.getStatusCode(), result.getStatusCode());
    }

    @Test
    void deleteSkillPositiveTest() {
        skillService.deleteSkill("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", 2L);
        Optional<Skill> skill = skillRepository.findById(2L);
        skill.ifPresent(value -> assertTrue(!value.getIsActive()));
    }

    @Test
    void updateSkillPositiveTest() {
        SkillDto dto = new SkillDto(6L, "html5");
        skillService.updateSkill("nuvhNSEgPFgr.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680857782010", dto);
        Optional<Skill> skill = skillRepository.findById(6L);
        skill.ifPresent(value -> assertTrue(value.getName().equals("Html5")));
    }
}
