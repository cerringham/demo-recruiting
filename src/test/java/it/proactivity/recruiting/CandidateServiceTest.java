package it.proactivity.recruiting;

import it.proactivity.recruiting.builder.CandidateWithSkillDtoBuilder;
import it.proactivity.recruiting.builder.SkillDtoBuilder;
import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateWithSkillDto;
import it.proactivity.recruiting.model.dto.SkillDto;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.service.CandidateService;
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
        Set<SkillDto> skillDtoSet = new HashSet<>();
        SkillDto skillDto1 = SkillDtoBuilder.newBuilder("Java").isActive(true).build();
        SkillDto skillDto2 = SkillDtoBuilder.newBuilder("JavaScript").isActive(true).build();
        skillDtoSet.add(skillDto1);
        skillDtoSet.add(skillDto2);
        CandidateWithSkillDto candidate = CandidateWithSkillDtoBuilder.newBuilder("Veronica").fiscalCode("TR830IT009084387")
                .surname("Zuniga").cityOfBirth("Lima").countryOfBirth("Peru").cityOfResidence("Torino").streetOfResidence("Via Roma 1")
                .regionOfResidence("Lombardy").countryOfResidence("Ita").email("vzfre7@gmail.com").phoneNumber("07704673000").gender("F")
                .isActive(true).birthDate("2000-06-22").expertise("Junior").skills(skillDtoSet).build();
        candidateService.insertNewCandidate(candidate);
        List<Candidate> candidates = candidateRepository.findByIsActive(true);
        assertTrue(candidates.contains(candidate));
        assertTrue(candidate.getName() == "Veronica");
    }

    @Test
    void insertCandidateNegativeTest() {
        Set<SkillDto> skillDtoSet = new HashSet<>();
        SkillDto skillDto1 = SkillDtoBuilder.newBuilder("Java").isActive(true).build();
        SkillDto skillDto2 = SkillDtoBuilder.newBuilder("JavaScript").isActive(true).build();
        skillDtoSet.add(skillDto1);
        skillDtoSet.add(skillDto2);
        CandidateWithSkillDto candidate = CandidateWithSkillDtoBuilder.newBuilder("Veronica").fiscalCode("TR830IT009084387")
                .surname("Zuniga").cityOfBirth("Lima").countryOfBirth("Peru").cityOfResidence("Torino").streetOfResidence("Via Roma 1")
                .regionOfResidence("Lombardy").countryOfResidence("Ita").email("vzfre7@gmail.com").phoneNumber("07704673000").gender("F")
                .isActive(true).birthDate("2000-06-22").expertise("Junior").skills(skillDtoSet).build();

        List<Candidate> actual = candidateRepository.findByIsActive(true);
        candidateService.insertNewCandidate(candidate);
        List<Candidate> later = candidateRepository.findByIsActive(true);
        assertFalse(actual.size() < later.size());
    }
}
