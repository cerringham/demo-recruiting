package it.proactivity.recruiting;

import it.proactivity.recruiting.builder.SkillLevelDtoBuilder;
import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.model.dto.JobPositionWithSkillsDto;
import it.proactivity.recruiting.model.dto.SkillLevelDto;
import it.proactivity.recruiting.repository.JobPositionRepository;
import it.proactivity.recruiting.service.JobPositionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
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
        Optional<JobPosition> jobPosition = jobPositionRepository.findById(16l);
        ResponseEntity response = jobPositionService.deleteJobPosition(jobPosition.get().getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteJobPositionNegativeTest() {
        ResponseEntity response = jobPositionService.deleteJobPosition(51l);

        assertNotEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateJobPositionPositiveTest() {
        ResponseEntity response = jobPositionService.updateJobPosition(15l, "Closed");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateJobPositionNegativeTest() {
        ResponseEntity response = jobPositionService.updateJobPosition(15l, "Abierto");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void insertJobPositionPositiveTest() {
        List<SkillLevelDto> skillLevels = new ArrayList<>();
        SkillLevelDto skill1 = SkillLevelDtoBuilder.newBuilder(true).skillName("Python").level("ADVANCED").build();
        SkillLevelDto skill2 = SkillLevelDtoBuilder.newBuilder(true).skillName("Java").level("ADVANCED").build();
        skillLevels.add(skill1);
        skillLevels.add(skill2);

        JobPositionWithSkillsDto jobPositionWithSkillsDto = new JobPositionWithSkillsDto("Python Developer",
                "Software Development","Description description description", "Milan", "Lombardy", "IT", true, "Fortitude",
                skillLevels);
        ResponseEntity response = jobPositionService.insertJobPosition(jobPositionWithSkillsDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void insertJobPositionEmptySkillLevelListTest() {
        List<SkillLevelDto> skillLevels = new ArrayList<>();

        JobPositionWithSkillsDto jobPositionWithSkillsDto = new JobPositionWithSkillsDto("Python Developer",
                "Software Development","Description description description", "Milan", "Lombardy", "IT", true, "Fortitude",
                skillLevels);
        ResponseEntity response = jobPositionService.insertJobPosition(jobPositionWithSkillsDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void insertJobPositionSomeNullFieldsTest() {
        List<SkillLevelDto> skillLevels = new ArrayList<>();
        SkillLevelDto skill1 = SkillLevelDtoBuilder.newBuilder(true).skillName("Python").level("ADVANCED").build();
        SkillLevelDto skill2 = SkillLevelDtoBuilder.newBuilder(true).skillName("Java").level("ADVANCED").build();
        skillLevels.add(skill1);
        skillLevels.add(skill2);

        JobPositionWithSkillsDto jobPositionWithSkillsDto = new JobPositionWithSkillsDto(null ,"Software Development",null,
                "Milan", "Lombardy", "IT", true, "Fortitude",
                skillLevels);
        ResponseEntity response = jobPositionService.insertJobPosition(jobPositionWithSkillsDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
