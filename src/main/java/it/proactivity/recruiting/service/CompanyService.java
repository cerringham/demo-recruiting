package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public ResponseEntity<List<Company>> getAllCompanies() {
       List<Company> companies= companyRepository.findAll();
       if (companies.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.of(Optional.of(companies));
    }

}
