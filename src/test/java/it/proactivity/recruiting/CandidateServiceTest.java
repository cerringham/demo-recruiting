package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateInformationDto;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.service.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
public class CandidateServiceTest {

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
        CandidateDto candidateDto = candidateService.findById(1l).getBody();
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

        Long numberOfCandidateBeforeInsert = candidateRepository.findByIsActive(true).stream().count();
        candidateService.insertCandidate(dto);

        Long numberOfCandidateAfterInsert = candidateRepository.findByIsActive(true).stream().count();

        assertTrue(numberOfCandidateBeforeInsert < numberOfCandidateAfterInsert);
    }

    @Test
    void insertCandidateNullMapNegativeTest() {
        CandidateInformationDto dto = new CandidateInformationDto("Gigi", "Castello", "FDRETU09O87L222I", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia", "Italia", "gigi.castello@gmail.it", "+39 8763483928",
                "male", "1995-12-09", "junior", null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidateService.insertCandidate(dto);
        });

        String message = "No skills or level found";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertCandidateEmptyMapNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        CandidateInformationDto dto = new CandidateInformationDto("Gigi", "Castello", "FDRETU09O87L222I", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia", "Italia", "gigi.castello@gmail.it", "+39 8763483928",
                "male", "1995-12-09", "junior", skillLevelMap);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidateService.insertCandidate(dto);
        });

        String message = "No skills or level found";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertCandidateNullNameAndSurnameNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("Azure", Level.ADVANCED);
        skillLevelMap.put("java", Level.BASIC);
        CandidateInformationDto dto = new CandidateInformationDto(null, null, "FDRETU09O87L222I", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia", "Italia", "gigi.castello@gmail.it", "+39 8763483928",
                "male", "1995-12-09", "junior", skillLevelMap);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidateService.insertCandidate(dto);
        });

        String message = "Name and surname can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertCandidateEmptyNameAndSurnameNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("Azure", Level.ADVANCED);
        skillLevelMap.put("java", Level.BASIC);
        CandidateInformationDto dto = new CandidateInformationDto("", "", "FDRETU09O87L222I", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia", "Italia", "gigi.castello@gmail.it", "+39 8763483928",
                "male", "1995-12-09", "junior", skillLevelMap);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidateService.insertCandidate(dto);
        });

        String message = "Name and surname can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertCandidateWrongExpertiseNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("Azure", Level.ADVANCED);
        skillLevelMap.put("java", Level.BASIC);
        CandidateInformationDto dto = new CandidateInformationDto("Gigi", "Castello", "FDRETU09O87L222I", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia", "Italia", "gigi.castello@gmail.it", "+39 8763483928",
                "male", "1995-12-09", "Fantastic", skillLevelMap);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidateService.insertCandidate(dto);
        });

        String message = "Expertise doesn't exists";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void deleteCandidatePositiveTest() {
        Long numberOfCandidateBeforeDelete = candidateRepository.findByIsActive(true).stream().count();

        candidateService.deleteCandidateById(1l);

        Long numberOfCandidateAfterDelete = candidateRepository.findByIsActive(true).stream().count();

        assertTrue(numberOfCandidateBeforeDelete > numberOfCandidateAfterDelete);
    }

    @Test
    void deleteCandidateCandidateNotFoundNegativeTest() {

        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        assertEquals(candidateService.deleteCandidateById(100l).getStatusCode(), response.getStatusCode());
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


        CandidateInformationDto dto = new CandidateInformationDto(7l, "Paola", "Liconasti", "LCNCST92P47H501Z", "Palermo",
                "Italia", "Roma", "Via  Tornabuoni", "Toscana", "Italia", "paola.liconasti@gmail.com", "+39 3204567890",
                "f", "1992-05-04", "Senior", skillLevelMap);

        candidateService.updateCandidate(dto);
    }
}
