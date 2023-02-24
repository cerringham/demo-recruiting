package it.proactivity.recruiting;


import it.proactivity.recruiting.model.dto.*;
import it.proactivity.recruiting.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
class RecruitingApplicationTests {

    @Autowired
    CandidateService candidateService;

    @Autowired
    CurriculumService curriculumService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRoleService companyRoleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ExpertiseService expertiseService;

    @Autowired
    JobInterviewService jobInterviewService;

    @Autowired
    JobInterviewStatusService jobInterviewStatusService;

    @Autowired
    JobInterviewTypeService jobInterviewTypeService;

    @Autowired
    JobPositionService jobPositionService;

    @Autowired
    JobPositionStatusService jobPositionStatusService;

    @Autowired
    SkillService skillService;

    @Autowired
    SkillLevelService skillLevelService;

    @Test
    void contextLoads() {
    }

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
    void getAllCurriculumTest() {
        List<CurriculumDto> dtoList = curriculumService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getCurriculumByIdTest() {
        CurriculumDto curriculumDto = curriculumService.getById(1l).getBody();
        assertNotNull(curriculumDto);
        System.out.println(curriculumDto);
    }

    @Test
    void getAllCompanyTest() {
        List<CompanyDto> dtoList = companyService.getdAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getCompanyByIdTest() {
        CompanyDto companyDto = companyService.getById(1l).getBody();
        assertNotNull(companyDto);
    }

    @Test
    void getAllCompanyRolesTest() {
        List<CompanyRoleDto> dtoList = companyRoleService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getCompanyRoleByIdTest() {
        CompanyRoleDto dto = companyRoleService.getById(1l).getBody();
        assertNotNull(dto);
    }

    @Test
    void getAllEmployeeTest() {
        Set<EmployeeDto> dtoList = employeeService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getEmployeeByIdTest() {
        EmployeeDto employeeDto = employeeService.getById(1l).getBody();
        assertNotNull(employeeDto);
        System.out.println(employeeDto);
    }

    @Test
    void getAllExpertiseTest() {
        List<ExpertiseDto> dtoList = expertiseService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getExpertiseByIdTest() {
        ExpertiseDto expertiseDto = expertiseService.getById(1l).getBody();
        assertNotNull(expertiseDto);
        System.out.println(expertiseDto);
    }

    @Test
    void getAllJobInterviewTest() {
        List<JobInterviewDto> dtoList = jobInterviewService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobInterviewByIdTest() {
        JobInterviewDto dto = jobInterviewService.getById(1l).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void getAllJobInterviewStatusTest() {
        List<JobInterviewStatusDto> dtoList = jobInterviewStatusService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobInterviewStatusByIdTest() {
        JobInterviewStatusDto dto = jobInterviewStatusService.getById(1l).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void getAllJobInterviewTypeTest() {
        List<JobInterviewTypeDto> dtoList = jobInterviewTypeService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobInterviewTypeByIdTest() {
        JobInterviewTypeDto dto = jobInterviewTypeService.getById(1l).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void getAllJobPositionTest() {
        List<JobPositionDto> dtoList = jobPositionService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getJobPositionByIdTest() {
        JobPositionDto dto = jobPositionService.getById(1l).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void getAllJobPositionStatusTest() {
        List<JobPositionStatusDto> dtoList = jobPositionStatusService.getAll().getBody();
        assertTrue(dtoList.size() != 0);

    }


    @Test
    void getJobPositionStatusByIdTest() {
        JobPositionStatusDto dto = jobPositionStatusService.getById(1l).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void getAllSkillsTest() {
        List<SkillDto> dtoList = skillService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getSkillByIdTest() {
        SkillDto dto = skillService.getById(1l).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }

    @Test
    void getAllSkillLevelTest() {
        List<SkillLevelDto> dtoList = skillLevelService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getSkillLevelByIdTest() {
        SkillLevelDto dto = skillLevelService.getById(1l).getBody();
        assertNotNull(dto);
        System.out.println(dto);
    }
}
