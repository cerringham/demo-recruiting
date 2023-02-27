package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.CandidateDtoBuilder;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.utility.ParsingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ParsingUtility parsingUtility;

    public ResponseEntity<Set<CandidateDto>> getAll() {
        List<Candidate> candidateList = candidateRepository.findAll();
        if (candidateList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Set<CandidateDto> candidateDtos = candidateList.stream().map(c -> new CandidateDto(c.getFiscalCode(),
                c.getName(), c.getSurname(), c.getCityOfBirth(), c.getCountryOfBirth(), c.getCityOfResidence(),
                c.getStreetOfResidence(), c.getRegionOfResidence(), c.getCountryOfResidence(), c.getEmail(),
                c.getPhoneNumber(), c.getGender(), c.getIsActive(), parsingUtility.parseLocalDateToString(c.getBirthDate())))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(candidateDtos);
    }

    public ResponseEntity<Optional<CandidateDto>> findById(Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<CandidateDto> candidateDto = Optional.ofNullable(createCandidateDto(candidate.get()));
        return ResponseEntity.ok(candidateDto);
    }

    public CandidateDto createCandidateDto(Candidate candidate) {
        return CandidateDtoBuilder.newBuilder(candidate.getFiscalCode())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .cityOfBirth(candidate.getCityOfBirth())
                .countryOfBirth(candidate.getCountryOfBirth())
                .cityOfResidence(candidate.getCityOfResidence())
                .streetOfResidence(candidate.getStreetOfResidence())
                .regionOfResidence(candidate.getRegionOfResidence())
                .countryOfResidence(candidate.getCountryOfResidence())
                .email(candidate.getEmail())
                .phoneNumber(candidate.getPhoneNumber())
                .gender(candidate.getGender())
                .isActive(candidate.getIsActive())
                .birthDate(parsingUtility.parseLocalDateToString(candidate.getBirthDate()))
                .build();
    }
}
