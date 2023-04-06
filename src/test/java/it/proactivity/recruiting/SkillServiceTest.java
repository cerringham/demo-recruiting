package it.proactivity.recruiting;


import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.SkillRepository;
import it.proactivity.recruiting.service.AccountService;
import it.proactivity.recruiting.service.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
 class SkillServiceTest {

    @Autowired
    SkillService skillService;
    @Autowired
    SkillRepository skillRepository;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    AccountService accountService;

    @Test
    void getAllSkillsTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        List<SkillDto> dtoList = skillService.getAll(token.get()).getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getSkillByIdTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        SkillDto dto = skillService.findById(token.get(), 1L).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void insertSkillPositiveTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        SkillDto dto = new SkillDto("laravel", true);
        long numberOfSkillBeforeInsert = skillRepository.findByIsActive(true).size();

        skillService.insertSkill(token.get(), dto);
        long numberOfSkillAfterInsert = skillRepository.findByIsActive(true).size();

        assertTrue(numberOfSkillBeforeInsert < numberOfSkillAfterInsert);
    }

    @Test
    void insertExistingSkillNegativeTest() {
        SkillDto dto = new SkillDto("laravel", true);

        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        ResponseEntity response = ResponseEntity.status(HttpStatus.FOUND).build();
        ResponseEntity result = skillService.insertSkill(token.get(), dto);

        assertEquals(response.getStatusCode(), result.getStatusCode());
    }

    @Test
    void deleteSkillPositiveTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        skillService.deleteSkill(token.get(), 2L);
        Optional<Skill> skill = skillRepository.findById(2L);
        skill.ifPresent(value -> assertTrue(!value.getIsActive()));
    }

    @Test
    void updateSkillPositiveTest() {
        SkillDto dto = new SkillDto(6L, "html5");
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        skillService.updateSkill(token.get(), dto);
        Optional<Skill> skill = skillRepository.findById(6L);
        skill.ifPresent(value -> assertTrue(value.getName().equals("Html5")));
    }

    private Optional<String> getToken(String accountUsername, String password) {
        LoginDto dto = new LoginDto(accountUsername, password);
        accountService.login(dto);
        return accessTokenRepository.findLatestTokenValueByUsername(accountUsername);
    }
}
