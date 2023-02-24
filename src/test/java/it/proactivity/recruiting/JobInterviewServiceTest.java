package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.service.JobInterviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class JobInterviewServiceTest {

    @Autowired
    JobInterviewService jobInterviewService;

    @Test
    void getAllJobInterviewTest() {
        List<JobInterviewDto> dtoList = jobInterviewService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobInterviewByIdTest() {
        JobInterviewDto dto = jobInterviewService.findById(1l).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }
}
