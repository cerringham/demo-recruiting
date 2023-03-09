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
        CompanyDto companyDto = companyService.findById(6l).getBody();
        assertNotNull(companyDto);
    }

    @Test
    void checkCompanyPresenceTest() {
        companyService.checkCompanyPresence();
        List<Company> companies = companyRepository.findAll();
        Optional<Company> fortitude = companyRepository.findByNameAndIsActive("Fortitude", true);
        assertTrue(companies.size() == 4);
        assertTrue(fortitude.isPresent());
    }

    @Test
    void deleteACompanyTest() {
        Optional<Company> company = companyRepository.findByName("RadicalBit");
        companyService.deleteCompanyByName("RadicalBit");
        List<Company> companyList = companyRepository.findByIsActive(true);
        System.out.println(companyList);
        assertTrue(companyList.size() == 3);
        assertTrue(!companyList.contains(company.get()));
    }

    @Test
    void deleteACompanyNegativeTest() {
        List<Company> companyList = companyRepository.findByIsActive(true);
        companyService.deleteCompanyByName("Company Random");
        List<Company> companiesAfterDelete = companyRepository.findByIsActive(true);
        System.out.println(companyList);
        assertTrue(companyList.size() == companiesAfterDelete.size());
    }

    @Test
    void ricreateACompanyPositiveTest() {
        companyService.checkCompanyPresence();
        Optional<Company> radicalbit = companyRepository.findByNameAndIsActive("RadicalBit", true);
        assertTrue(radicalbit.isPresent());
    }

    @Test
    void deleteTwoCompaniesAndRicreateTest() {
        companyService.deleteCompanyByName("RadicalBit");
        companyService.deleteCompanyByName("Fortitude");
        Optional<Company> company = companyRepository.findByNameAndIsActive("Fortitude", true);
        List<Company> companies = companyRepository.findByIsActive(true);
        assertTrue(companies.size() == 2);
        assertTrue(company.isEmpty());
        companyService.checkCompanyPresence();
        List<Company> companiesAfterCheck = companyRepository.findByIsActive(true);
        assertTrue(companiesAfterCheck.size() == 4);
    }

   @Test
    void falseCompaniesAndRicreateTest() {
        Optional<Company> proactivity = companyRepository.findByName("Proactivity");
        Optional<Company> fortitude = companyRepository.findByName("Fortitude");
        proactivity.get().setIsActive(false);
        fortitude.get().setIsActive(false);
        companyRepository.save(proactivity.get());
        companyRepository.save(fortitude.get());
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
