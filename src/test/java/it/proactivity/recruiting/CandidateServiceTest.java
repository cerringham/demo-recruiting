package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.service.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class CandidateServiceTest {

    @Autowired
    CandidateService candidateService;

    @Test
    void getAllCandidateTest() {
        Set<CandidateDto> dtoList = candidateService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getCandidateById() {
        CandidateDto candidateDto = candidateService.findById(1l).getBody();
        assertNotNull(candidateDto);
    }
}
