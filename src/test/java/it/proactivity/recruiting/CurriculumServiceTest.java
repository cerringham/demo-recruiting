package it.proactivity.recruiting;

import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.service.AccountService;
import it.proactivity.recruiting.service.CurriculumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
 class CurriculumServiceTest {

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    CurriculumService curriculumService;

    @Test
    void getAllCurriculumTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        List<CurriculumDto> dtoList = curriculumService.getAll(token.get()).getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getCurriculumByIdTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        CurriculumDto curriculumDto = curriculumService.findById(token.get(), 1L).getBody();
        assertNotNull(curriculumDto);
        System.out.println(curriculumDto);
    }

    private Optional<String> getToken(String accountUsername, String password) {
        LoginDto dto = new LoginDto(accountUsername, password);
        accountService.login(dto);
        return accessTokenRepository.findLatestTokenValueByUsername(accountUsername);
    }
}
