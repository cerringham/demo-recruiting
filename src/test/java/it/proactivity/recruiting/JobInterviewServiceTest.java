package it.proactivity.recruiting;


import it.proactivity.recruiting.model.JobInterview;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.model.dto.JobInterviewInsertionDto;
import it.proactivity.recruiting.model.dto.JobInterviewUpdateDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import it.proactivity.recruiting.repository.AccessTokenRepository;
import it.proactivity.recruiting.repository.JobInterviewRepository;
import it.proactivity.recruiting.service.AccountService;
import it.proactivity.recruiting.service.JobInterviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
class JobInterviewServiceTest {

    @Autowired
    JobInterviewService jobInterviewService;
    @Autowired
    JobInterviewRepository jobInterviewRepository;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    AccountService accountService;

    private final ResponseEntity POSITIVE_RESPONSE = ResponseEntity.status(HttpStatus.OK).build();

    @Test
    void getAllJobInterviewTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        List<JobInterviewDto> dtoList = jobInterviewService.getAll(token.get()).getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobInterviewByIdTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        JobInterviewDto dto = jobInterviewService.findById(token.get(), 1L).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void createJobInterviewPositiveTest() {
        JobInterviewInsertionDto dto = new JobInterviewInsertionDto("12:00", "2023-03-25", "Milan",
                "Failed", 4L, 5L, 1L);

        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        ResponseEntity response = jobInterviewService.createJobInterview(token.get(), dto);

        assertEquals(POSITIVE_RESPONSE.getStatusCode(), response.getStatusCode());

    }

    @Test
    void updateJobInterviewPositiveTest() {
        JobInterviewUpdateDto dto = new JobInterviewUpdateDto(31L, "2023-03-23", "17:00", "1", "Good candidate",
                7L);

        JobInterview jobInterviewBeforeUpdate = jobInterviewRepository.findById(31L).get();
        assertTrue(jobInterviewBeforeUpdate.getRating() == 10);

        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");

        jobInterviewService.updateJobInterview(token.get(), dto);

        JobInterview jobInterviewAfterUpdate = jobInterviewRepository.findById(31L).get();

        assertTrue(jobInterviewAfterUpdate.getRating() == 1);

    }

    @Test
    void deleteJobInterviewPositiveTest() {
        Optional<String> token = getToken("veronica.zuniga@proactivity.it", "Password2!");
        jobInterviewService.deleteJobInterview(token.get(), 31L);

        JobInterview jobInterview = jobInterviewRepository.findById(31L).get();

        assertFalse(jobInterview.getIsActive());
    }

    private Optional<String> getToken(String accountUsername, String password) {
        LoginDto dto = new LoginDto(accountUsername, password);
        accountService.login(dto);
        return accessTokenRepository.findLatestTokenValueByUsername(accountUsername);
    }
}
