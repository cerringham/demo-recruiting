package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.service.CandidateService;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
public class CandidateServiceTest {

    @Autowired
    CandidateService candidateService;
    @Autowired
    GlobalValidator globalValidator;

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

    @Test
    void validateBirthDateTest() {
        String date = "2005-02-28";
        assertTrue(globalValidator.validateBirthDate(date));
    }

    @Test
    void validatePhoneNumber() {
        String phoneNumber = "+37884564980";
        assertTrue(globalValidator.validatePhoneNumber(phoneNumber));
    }
}
