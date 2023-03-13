package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.service.CurriculumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
 class CurriculumServiceTest {

    @Autowired
    CurriculumService curriculumService;

    @Test
    void getAllCurriculumTest() {
        List<CurriculumDto> dtoList = curriculumService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getCurriculumByIdTest() {
        CurriculumDto curriculumDto = curriculumService.findById(1L).getBody();
        assertNotNull(curriculumDto);
        System.out.println(curriculumDto);
    }
}
