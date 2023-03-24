package it.proactivity.recruiting;

import it.proactivity.recruiting.model.JobInterview;
import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.repository.JobInterviewRepository;
import it.proactivity.recruiting.repository.JobPositionRepository;
import it.proactivity.recruiting.service.JobInterviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.w3c.dom.html.HTMLTableCaptionElement;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
 class JobInterviewServiceTest {

    @Autowired
    JobInterviewService jobInterviewService;

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Autowired
    JobInterviewRepository jobInterviewRepository;

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
    void insertJobInterviewPositiveTest() {
        Optional<JobPosition> jobPosition = jobPositionRepository.findByTitle("Project Manager");
        JobInterviewDto jobInterviewDto = new JobInterviewDto("2023-09-16", "11:00", 5l, 16l, "Online", jobPosition.get().getId());
        List<JobInterview> jobInterviewListBefore = jobInterviewRepository.findAll();

        ResponseEntity response = jobInterviewService.insertJobInterview(jobInterviewDto);

        List<JobInterview> jobInterviewListAfter = jobInterviewRepository.findAll();

        assertEquals(HttpStatus.CREATED, (response.getStatusCode()));
        assertTrue(jobInterviewListBefore.size() + 1 == jobInterviewListAfter.size());
    }

    @Test
    void insertJobPositionNegativeTest() {
        Optional<JobPosition> jobPosition = jobPositionRepository.findByTitle("Project Manager");
        //wrong data
        JobInterviewDto jobInterviewDto = new JobInterviewDto("2023-13-09", "11:00", 5l, 16l, "Online", jobPosition.get().getId());
        List<JobInterview> jobInterviewListBefore = jobInterviewRepository.findAll();
        try {
            ResponseEntity response = jobInterviewService.insertJobInterview(jobInterviewDto);
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Date cannot be parsed");
        }

        List<JobInterview> jobInterviewListAfter = jobInterviewRepository.findAll();
        assertTrue(jobInterviewListBefore.size() == jobInterviewListAfter.size());
    }

    @Test
    void updateJobInterviewPositiveTest() {
        JobInterviewDto jobInterviewDto = new JobInterviewDto(11l, "2023-05-10", "08:00", 5l, 4, "note note note", true);
        ResponseEntity response = jobInterviewService.updateJobInterview(jobInterviewDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateJobInterviewNegativeTest() {
        //wrong hour
        JobInterviewDto jobInterviewDto = new JobInterviewDto(11l, "2023-05-10", "08.00", 5l, 4, "note note note", true);
        try {
            ResponseEntity response = jobInterviewService.updateJobInterview(jobInterviewDto);
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Time cannot be parsed");
        }
    }
}
