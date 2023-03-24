package it.proactivity.recruiting;


import it.proactivity.recruiting.model.JobInterview;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.model.dto.JobInterviewInsertionDto;
import it.proactivity.recruiting.model.dto.JobInterviewUpdateDto;
import it.proactivity.recruiting.repository.JobInterviewRepository;
import it.proactivity.recruiting.service.JobInterviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
class JobInterviewServiceTest {

    @Autowired
    JobInterviewService jobInterviewService;
    @Autowired
    JobInterviewRepository jobInterviewRepository;
    private final ResponseEntity POSITIVE_RESPONSE = ResponseEntity.status(HttpStatus.OK).build();

    @Test
    void getAllJobInterviewTest() {
        List<JobInterviewDto> dtoList = jobInterviewService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobInterviewByIdTest() {
        JobInterviewDto dto = jobInterviewService.findById(1L).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void createJobInterviewPositiveTest() {
        JobInterviewInsertionDto dto = new JobInterviewInsertionDto("12:00", "2023-03-25", "Milan",
                "Failed", 4L, 5L, 1L);

        ResponseEntity response = jobInterviewService.createJobInterview(dto);

        assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void updateJobInterviewPositiveTest() {
        JobInterviewUpdateDto dto = new JobInterviewUpdateDto(31L, "2023-03-23", "17:00", "1", "Good candidate",
                7L);

        JobInterview jobInterviewBeforeUpdate = jobInterviewRepository.findById(31L).get();
        assertTrue(jobInterviewBeforeUpdate.getRating() == 10);

        jobInterviewService.updateJobInterview(dto);

        JobInterview jobInterviewAfterUpdate = jobInterviewRepository.findById(31L).get();

        assertTrue(jobInterviewAfterUpdate.getRating() == 1);

    }

    @Test
    void deleteJobInterviewPositiveTest() {
        jobInterviewService.deleteJobInterview(31L);

        JobInterview jobInterview = jobInterviewRepository.findById(31L).get();

        assertFalse(jobInterview.getIsActive());
    }
}
