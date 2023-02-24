package it.proactivity.recruiting;


import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateSkillDto;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.service.CandidateService;
import it.proactivity.recruiting.service.CandidateSkillService;
import it.proactivity.recruiting.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
class RecruitingApplicationTests {

	@Autowired
	CandidateService candidateService;

	@Autowired
	CandidateSkillService candidateSkillService;

	@Autowired
	CompanyService companyService;

	@Test
	void contextLoads() {
	}

	@Test
	void findAllCandidateTest() {
		List<CandidateDto>  dtoList = candidateService.findAll().getBody();
		assertTrue(dtoList.size() != 0);
	}

	@Test
	void findCandidateById() {
		CandidateDto candidateDto = candidateService.findById(1l).getBody();
		assertNotNull(candidateDto);
	}

	@Test
	void findAllCandidateSkillTest() {
		List<CandidateSkillDto> dtoList = candidateSkillService.findAll().getBody();
		assertTrue(dtoList.size() != 0);
	}

	@Test
	void findCandidateSkillByIdTest() {
		CandidateSkillDto candidateSkillDto = candidateSkillService.findById(1l).getBody();
		assertNotNull(candidateSkillDto);
	}

	@Test
	void findAllCompanyTest() {
		List<CompanyDto> dtoList = companyService.findAll().getBody();
		assertTrue(dtoList.size() != 0);
	}

	@Test
	void findCompanyByIdTest() {
		CompanyDto companyDto = companyService.findById(1l).getBody();
		assertNotNull(companyDto);
	}
}
