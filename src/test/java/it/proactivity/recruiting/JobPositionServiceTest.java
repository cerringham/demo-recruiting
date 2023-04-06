package it.proactivity.recruiting;

import it.proactivity.recruiting.builder.SkillLevelDtoBuilder;
import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.model.dto.JobPositionWithSkillsDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.model.dto.SkillLevelDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.JobPositionRepository;
import it.proactivity.recruiting.service.AccountService;
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

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    AccountService accountService;

    @Test
    void getAllJobPositionTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        List<JobPositionDto> dtoList = jobPositionService.getAll(token.get()).getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobPositionByIdTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        JobPositionDto dto = jobPositionService.findById(token.get(), 11L).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void deleteJobPositionPositiveTest() {
        Optional<JobPosition> jobPosition = jobPositionRepository.findById(16l);
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        ResponseEntity response = jobPositionService.deleteJobPosition(token.get(), jobPosition.get().getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteJobPositionNegativeTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        ResponseEntity response = jobPositionService.deleteJobPosition(token.get(), 51l);

        assertNotEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateJobPositionPositiveTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        ResponseEntity response = jobPositionService.updateJobPosition(token.get(), 15l, "Closed");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateJobPositionNegativeTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        ResponseEntity response = jobPositionService.updateJobPosition(token.get(), 15l, "Abierto");

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
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        ResponseEntity response = jobPositionService.insertJobPosition(token.get(), jobPositionWithSkillsDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void insertJobPositionEmptySkillLevelListTest() {
        List<SkillLevelDto> skillLevels = new ArrayList<>();

        JobPositionWithSkillsDto jobPositionWithSkillsDto = new JobPositionWithSkillsDto("Python Developer",
                "Software Development","Description description description", "Milan", "Lombardy", "IT", true, "Fortitude",
                skillLevels);

        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        ResponseEntity response = jobPositionService.insertJobPosition(token.get(), jobPositionWithSkillsDto);

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
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        ResponseEntity response = jobPositionService.insertJobPosition(token.get(), jobPositionWithSkillsDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private Optional<String> getToken(String accountUsername, String password) {
        LoginDto dto = new LoginDto(accountUsername, password);
        accountService.login(dto);
        return accessTokenRepository.findLatestTokenValueByUsername(accountUsername);
    }
}
