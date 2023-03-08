package it.proactivity.recruiting;

import it.proactivity.recruiting.builder.CompanyBuilder;
import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.dto.CompanyDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    void getAllCompanyTest() {
        List<CompanyDto> dtoList = companyService.getAll().getBody();
        assertTrue(dtoList.size() != 0);
    }

    @Test
    void getCompanyByIdTest() {
        CompanyDto companyDto = companyService.findById(1l).getBody();
        assertNotNull(companyDto);
    }
    @Test
    void allCompaniesPresentTest() {
        companyService.checkCompanyPresence();
        List<Company> companyList = companyRepository.findByIsActive(true);
        assertTrue(companyList.size() == 4);
    }

    @Test
    void deleteACompanyTest() {
        companyService.deleteCompanyById(4l);
        List<Company> companyList = companyRepository.findByIsActive(true);
        System.out.println(companyList);
        assertTrue(companyList.size() == 3);
    }

    @Test
    void deleteACompanyNegativeTest() {
        List<Company> companyList = companyRepository.findByIsActive(true);
        companyService.deleteCompanyById(6l);
        List<Company> companiesAfterDelete = companyRepository.findByIsActive(true);
        System.out.println(companyList);
        assertTrue(companyList.size() == companiesAfterDelete.size());
    }

    @Test
    void ricreateACompanyPositiveTest() {
        companyService.checkCompanyPresence();
        List<Company> companies = companyRepository.findByIsActive(true);
        assertTrue(companies.size() == 4);
    }

    @Test
    void deleteTwoCompaniesAndRicreateTest() {
        companyService.deleteCompanyById(1l);
        companyService.deleteCompanyById(3l);
        List<Company> companies = companyRepository.findByIsActive(true);
        assertTrue(companies.size() == 2);
        companyService.checkCompanyPresence();
        List<Company> companiesAfterCheck = companyRepository.findByIsActive(true);
        assertTrue(companiesAfterCheck.size() == 4);
    }

    @Test
    void falseCompaniesAndRicreateTest() {
        Optional<Company> proactivity = companyRepository.findByNameIgnoreCaseAndIsActive("Proactivity", true);
        Optional<Company> radicalbit = companyRepository.findByNameIgnoreCaseAndIsActive("Radicalbit", true);
        proactivity.get().setIsActive(false);
        radicalbit.get().setIsActive(false);
        companyRepository.save(proactivity.get());
        companyRepository.save(radicalbit.get());
        List<Company> companies = companyRepository.findByIsActive(true);
        assertTrue(companies.size() == 2);
        companyService.checkCompanyPresence();
        List<Company> companiesAfterCheck = companyRepository.findByIsActive(true);
        assertTrue(companiesAfterCheck.size() == 4);
    }

    @Test
    void moreThan4CompaniesTest() {
        Company company = CompanyBuilder.newBuilder("New Company").isActive(true).build();
        List<Company> companies = companyRepository.findByIsActive(true);
        companyService.checkCompanyPresence();
        assertFalse(companies.size() == 5);
        assertFalse(companies.contains(company));
    }
}
