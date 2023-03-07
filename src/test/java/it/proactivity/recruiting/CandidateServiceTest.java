package it.proactivity.recruiting;

import it.proactivity.recruiting.builder.CandidateBuilder;
import it.proactivity.recruiting.builder.SkillBuilder;
import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateWithSkillDto;
import it.proactivity.recruiting.repository.CurriculumRepository;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import it.proactivity.recruiting.service.CandidateService;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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


}
