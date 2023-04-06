package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateInformationDto;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.service.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
class CandidateServiceTest {

    @Autowired
    CandidateService candidateService;
    @Autowired
    CandidateRepository candidateRepository;

    @Test
    void getAllCandidateTest() {
        Set<CandidateDto> dtoList = candidateService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getCandidateById() {
        CandidateDto candidateDto = candidateService.findById(1L).getBody();
        assertNotNull(candidateDto);
    }

    @Test
    void insertCandidatePositiveTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("Azure", Level.ADVANCED);
        skillLevelMap.put("java", Level.BASIC);

        CandidateInformationDto dto = new CandidateInformationDto("Gigi", "Castello", "FDRETU09O87L222I", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia", "Italia", "gigi.castello@gmail.it", "+39 8763483928",
                "m", "1995-12-09", "junior", skillLevelMap);

        long numberOfCandidateBeforeInsert = candidateRepository.findByIsActive(true).size();
        candidateService.insertCandidate("PzEnDpYUZEVv.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680794469011",dto);

        long numberOfCandidateAfterInsert = candidateRepository.findByIsActive(true).size();

        assertTrue(numberOfCandidateBeforeInsert < numberOfCandidateAfterInsert);
    }

    @Test
    void insertCandidateNEgativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("Azure", Level.ADVANCED);
        skillLevelMap.put("java", Level.BASIC);

        CandidateInformationDto dto = new CandidateInformationDto("Gigi", "Castello", "FDROTU09O87L222I", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia", "Italia", "gigicastello@gmail.it", "+39 8763403928",
                "m", "1995-12-09", "junior", skillLevelMap);

        long numberOfCandidateBeforeInsert = candidateRepository.findByIsActive(true).size();
        //non-existing accessToken
        candidateService.insertCandidate("nzEnDpYUZEVv.dmVyb25pY2F6dW5pZ2FAZ21haWwuY29t.1680794469011",dto);

        long numberOfCandidateAfterInsert = candidateRepository.findByIsActive(true).size();

        assertTrue(numberOfCandidateBeforeInsert == numberOfCandidateAfterInsert);
    }

    @Test
    void deleteCandidatePositiveTest() {
        long numberOfCandidateBeforeDelete = candidateRepository.findByIsActive(true).size();

        candidateService.deleteCandidateById(1L);

        long numberOfCandidateAfterDelete = candidateRepository.findByIsActive(true).size();

        assertTrue(numberOfCandidateBeforeDelete > numberOfCandidateAfterDelete);
    }

    @Test
    void deleteCandidateCandidateNotFoundNegativeTest() {

        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        assertEquals(candidateService.deleteCandidateById(100L).getStatusCode(), response.getStatusCode());
    }

    @Test
    void updateCandidatePositiveTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("CSS", Level.ADVANCED);//7
        skillLevelMap.put("JavaScript", Level.BASIC);//5
        skillLevelMap.put("C++", Level.INTERMEDIATE);//4
        skillLevelMap.put("SQL", Level.ADVANCED);//3
        skillLevelMap.put("Python", Level.BASIC);//2
        skillLevelMap.put("vue", Level.BASIC);//10


        CandidateInformationDto dto = new CandidateInformationDto(7L, "Paola", "Liconasti",
                "LCNCST92P47H501Z", "Palermo", "Italia", "Roma",
                "Via  Tornabuoni", "Toscana", "Italia",
                "paola.liconasti@gmail.com", "+39 3204567890", "f", "1992-05-04",
                "Senior", skillLevelMap);

        candidateService.updateCandidate(dto);


    }
}
