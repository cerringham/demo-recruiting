package it.proactivity.recruiting;

import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.repository.JobPositionRepository;
import it.proactivity.recruiting.service.JobPositionService;
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
 class JobPositionServiceTest {

    @Autowired
    JobPositionService jobPositionService;

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Test
    void getAllJobPositionTest() {
        List<JobPositionDto> dtoList = jobPositionService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobPositionByIdTest() {
        JobPositionDto dto = jobPositionService.findById(11L).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void deleteJobPositionPositiveTest() {
        Optional<JobPosition> jobPosition = jobPositionRepository.findById(15l);
        ResponseEntity response = jobPositionService.deleteJobPosition(jobPosition.get().getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateJobPositionPositiveTest() {
        Optional<JobPosition> jobPosition = jobPositionRepository.findById(14l);

    }

}
