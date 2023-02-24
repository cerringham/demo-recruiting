package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.utility.CandidateValidator;
import it.proactivity.recruiting.utility.ParsingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ParsingUtility parsingUtility;

    @Autowired
    CandidateValidator candidateValidator;

    public ResponseEntity<List<CandidateDto>> findAll() {

        List<Candidate> candidateList = candidateRepository.findAll();

        List<CandidateDto> dtoList = candidateList.stream()
                .map(c -> new CandidateDto(c.getFiscalCode(), c.getName(), c.getSurname(), c.getCityOfBirth(),
                        c.getCountryOfBirth(), c.getCityOfResidence(), c.getStreetOfResidence(), c.getRegionOfResidence(),
                        c.getCountryOfResidence(), c.getEmail(), c.getPhoneNumber(), c.getGender(), c.getIsActive(),
                        parsingUtility.parseDateToString(c.getBirthDate())))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CandidateDto> findById(Long id) {
        candidateValidator.validateId(id);

        Optional<Candidate> candidate = candidateRepository.findById(id);

        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new CandidateDto(candidate.get().getFiscalCode(), candidate.get().getName(),
                candidate.get().getSurname(), candidate.get().getCityOfBirth(), candidate.get().getCountryOfBirth(),
                candidate.get().getCityOfResidence(), candidate.get().getStreetOfResidence(), candidate.get().getRegionOfResidence(),
                candidate.get().getCountryOfResidence(), candidate.get().getEmail(), candidate.get().getPhoneNumber(),
                candidate.get().getGender(), candidate.get().getIsActive(),
                parsingUtility.parseDateToString(candidate.get().getBirthDate())));
    }
}
