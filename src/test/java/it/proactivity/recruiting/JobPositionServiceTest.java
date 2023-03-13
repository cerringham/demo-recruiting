package it.proactivity.recruiting;

import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.model.dto.JobPositionInsertionDto;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.JobPositionRepository;
import it.proactivity.recruiting.service.JobPositionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
class JobPositionServiceTest {

    @Autowired
    JobPositionService jobPositionService;

    @Autowired
    JobPositionRepository jobPositionRepository;

    private final ResponseEntity POSITIVE_RESPONSE = ResponseEntity.status(HttpStatus.OK).build();

    @Test
    void getAllJobPositionTest() {
        List<JobPositionDto> dtoList = jobPositionService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobPositionByIdTest() {
        JobPositionDto dto = jobPositionService.findById(1L).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void insertJobPositionWithExistenceSkillsPositiveTest() {

        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("angular", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Roma", "Lazio",
                "Italy", true, "proactivity", skillLevelMap);

        long numberOfJobPositionBeforeInsert = jobPositionRepository.findByIsActive(true).size();

        jobPositionService.insertJobPosition(dto);

        long numberOfJobPositionAfterInsert = jobPositionRepository.findByIsActive(true).size();

        assertTrue(numberOfJobPositionBeforeInsert < numberOfJobPositionAfterInsert);
    }

    @Test
    void insertJobPositionWithOneExistenceSkillAndOneNewPositiveTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("LaravelNova", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Roma", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        long numberOfJobPositionBeforeInsert = jobPositionRepository.findByIsActive(true).size();

        jobPositionService.insertJobPosition(dto);

        long numberOfJobPositionAfterInsert = jobPositionRepository.findByIsActive(true).size();

        assertTrue(numberOfJobPositionBeforeInsert < numberOfJobPositionAfterInsert);
    }

    @Test
    void updateJobPositionPositiveTest() {
        JobPositionInsertionDto dto = new JobPositionInsertionDto(1L, "Closed");

        jobPositionService.updateJobPosition(dto);

        JobPosition jobPosition = jobPositionRepository.findById(1L).get();
        assertTrue(jobPosition.getJobPositionStatus().getName().equals("Closed"));
    }

    @Test
    void deleteJobPositionPositiveTest() {


        ResponseEntity response = jobPositionService.deleteJobPosition(2L);

        JobPosition jobPosition = jobPositionRepository.findById(2L).get();

        assertTrue(!jobPosition.getIsActive());
        assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());
    }

    @Test
    void insertJobPositionDtoNullNegativeTest() {

        ResponseEntity response = jobPositionService.insertJobPosition(null);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionDtoMapNullNegativeTest() {
        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Roma", "Lazio",
                "Italy", true, "bitrock", null);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());

    }

    @Test
    void insertJobPositionSkillLessThan2NegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);


        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Roma", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionSkillsWithoutLevelNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", null);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Roma", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionTitleNullNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto(null, "Software Developer",
                "We are looking for Back end developer with one year experience", "Roma", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());

    }

    @Test
    void insertJobPositionTitleEmptyNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("", "Software Developer",
                "We are looking for Back end developer with one year experience", "Roma", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());

    }

    @Test
    void insertJobPositionTitleNotAlphaSpaceNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back-End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Roma", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());

    }

    @Test
    void insertJobPositionWithAreaNullNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", null,
                "We are looking for Back end developer with one year experience", "Roma", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithAreaEmptyNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "",
                "We are looking for Back end developer with one year experience", "Roma", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithDescriptionNullNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                null, "Roma", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithDescriptionEmptyNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "", "Roma", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithCityNullNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", null, "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithCityEmptyNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "", "Lazio",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithRegionNullNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Milano", null,
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithRegionEmptyNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Milano", "",
                "Italy", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithCountryNullNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Milano", "Lombardia",
                null, true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithCountryEmptyNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Milano", "Lombardia",
                "", true, "bitrock", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithCompanyNameNullNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Milano", "Lombardia",
                "Italy", true, null, skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void insertJobPositionWithCompanyNameEmptyNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("java", Level.ADVANCED);
        skillLevelMap.put("c#", Level.INTERMEDIATE);

        JobPositionInsertionDto dto = new JobPositionInsertionDto("Back End developer", "Software Developer",
                "We are looking for Back end developer with one year experience", "Milano", "Lombardia",
                "Italy", true, "", skillLevelMap);

        ResponseEntity response = jobPositionService.insertJobPosition(dto);

        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }
}
