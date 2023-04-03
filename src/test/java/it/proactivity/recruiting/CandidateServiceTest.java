package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateInformationDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.service.AccountService;
import it.proactivity.recruiting.service.CandidateService;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
class CandidateServiceTest {

    @Autowired
    CandidateService candidateService;
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    AccountService accountService;
    @Autowired
    private AccessTokenRepository accessTokenRepository;

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


        Optional<String> token = getToken("luigi.cerrato@proactivity.it", "Password3!");
        CandidateInformationDto dto = new CandidateInformationDto("Gigi", "Castello", "FBRELU09O87L222I", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia", "Italia", "gigi2.castello@gmail.it", "+39 8993483928",
                "m", "1995-12-09", "junior", skillLevelMap);

        long numberOfCandidateBeforeInsert = candidateRepository.findByIsActive(true).size();

        candidateService.insertCandidate(dto, token.get());

        long numberOfCandidateAfterInsert = candidateRepository.findByIsActive(true).size();

        assertTrue(numberOfCandidateBeforeInsert < numberOfCandidateAfterInsert);
    }

    @Test
    void insertCandidateUnauthorizedNegativeTest() {
        Map<String, Level> skillLevelMap = new HashMap<>();
        skillLevelMap.put("Azure", Level.ADVANCED);
        skillLevelMap.put("java", Level.BASIC);


        Optional<String> token = getToken("alessio.cassarino@proactivity.it", "Password1!");
        CandidateInformationDto dto = new CandidateInformationDto("Gigi", "Castello", "FDRELU09O87L222I", "Catania",
                "Italia", "Catania", "via catania 23", "Sicilia", "Italia", "gigi1.castello@gmail.it", "+39 8963483928",
                "m", "1995-12-09", "junior", skillLevelMap);



        ResponseEntity response = candidateService.insertCandidate(dto, token.get());
        assertTrue(response.getStatusCode().equals(HttpStatus.UNAUTHORIZED));
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

    private Optional<String> getToken(String accountUsername, String password) {
        LoginDto dto = new LoginDto(accountUsername, password);
        accountService.login(dto);
        return accessTokenRepository.findLatestTokenValueByUsername(accountUsername);
    }
}
