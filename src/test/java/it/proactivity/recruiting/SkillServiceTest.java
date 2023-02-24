package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.service.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class SkillServiceTest {

    @Autowired
    SkillService skillService;

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
}
