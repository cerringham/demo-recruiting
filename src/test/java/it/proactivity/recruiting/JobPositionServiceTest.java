package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.service.JobPositionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class JobPositionServiceTest {

    @Autowired
    JobPositionService jobPositionService;

    @Test
    void getAllJobPositionTest() {
        List<JobPositionDto> dtoList = jobPositionService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobPositionByIdTest() {
        JobPositionDto dto = jobPositionService.findById(1l).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }
}
