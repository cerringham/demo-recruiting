package it.proactivity.recruiting;

import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateWithSkillDto;
import it.proactivity.recruiting.repository.CurriculumRepository;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import it.proactivity.recruiting.service.CandidateService;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
public class CandidateServiceTest {

    @Autowired
    CandidateService candidateService;
    @Autowired
    ExpertiseRepository expertiseRepository;

    @Autowired
    CurriculumRepository curriculumRepository;

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
    void insertNewCandidateTest() {
        Optional<Expertise> expertise = expertiseRepository.findByNameAndIsActive("Junior", true);
        CandidateWithSkillDto candidate = new CandidateWithSkillDto("TRGFBIJEUTY16387S", "Veronica", "Zuniga", "Lima", "Peru",
                "Milan", "corso milano 40", "Lombardy", "Ita", "veronicazuniga@gmail.com", "3677648655", "F", true,
                "2000-06-22", expertise , );
        CandidateDto candidateDto = candidateService.insertNewCandidate();
    }

}
