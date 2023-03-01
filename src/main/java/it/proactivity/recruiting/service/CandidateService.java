package it.proactivity.recruiting.service;

import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.model.dto.CandidateWithSkillDto;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import it.proactivity.recruiting.utility.CandidateUtility;
import it.proactivity.recruiting.utility.CandidateValidator;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.ParsingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    CandidateValidator candidateValidator;

    @Autowired
    CandidateUtility candidateUtility;

    @Autowired
    ExpertiseRepository expertiseRepository;

    public ResponseEntity<Set<CandidateDto>> getAll() {

        List<Candidate> candidateList = candidateRepository.findByIsActive(true);

        Set<CandidateDto> dtoList = candidateList.stream()
                .map(c -> candidateUtility.createCandidateDto(c.getFiscalCode(), c.getName(), c.getSurname(), c.getCityOfBirth(),
                        c.getCountryOfBirth(), c.getCityOfResidence(), c.getStreetOfResidence(), c.getRegionOfResidence(),
                        c.getCountryOfResidence(), c.getEmail(), c.getPhoneNumber(), c.getGender(), c.getIsActive(),
                        parsingUtility.parseDateToString(c.getBirthDate())))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CandidateDto> findById(Long id) {
        globalValidator.validateId(id);

        Optional<Candidate> candidate = candidateRepository.findByIdAndIsActive(id, true);

        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(candidateUtility.createCandidateDto(candidate.get().getFiscalCode(), candidate.get().getName(),
                candidate.get().getSurname(), candidate.get().getCityOfBirth(), candidate.get().getCountryOfBirth(),
                candidate.get().getCityOfResidence(), candidate.get().getStreetOfResidence(),
                candidate.get().getRegionOfResidence(), candidate.get().getCountryOfResidence(),
                candidate.get().getEmail(), candidate.get().getPhoneNumber(), candidate.get().getGender(),
                candidate.get().getIsActive(), parsingUtility.parseDateToString(candidate.get().getBirthDate())));
    }

    public ResponseEntity<CandidateWithSkillDto> insertNewCandidate(CandidateWithSkillDto candidateWithSkillDto) {
        if (!candidateValidator.validateCandidate(candidateWithSkillDto)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Expertise> expertise = expertiseRepository.findByNameAndIsActive(candidateWithSkillDto.getExpertise().getName(), true);
        if (expertise.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Candidate candidate = candidateUtility.createCandidateFromCandidateWithSkillDto(candidateWithSkillDto);
        candidateRepository.save(candidate);
        return ResponseEntity.ok(candidateWithSkillDto);
    }

    public ResponseEntity<CandidateDto> deleteById(Long id) {
        globalValidator.validateId(id);
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        candidate.get().setIsActive(false);
        candidateRepository.save(candidate.get());
        CandidateDto candidateDto = candidateUtility.createCandidateDto(candidate.get().getName(), candidate.get().getFiscalCode(),
                candidate.get().getSurname(), candidate.get().getCityOfBirth(), candidate.get().getCountryOfBirth(),
                candidate.get().getCityOfResidence(), candidate.get().getStreetOfResidence(),
                candidate.get().getRegionOfResidence(), candidate.get().getCountryOfResidence(),
                candidate.get().getEmail(), candidate.get().getPhoneNumber(), candidate.get().getGender(),
                candidate.get().getIsActive(), parsingUtility.parseDateToString(candidate.get().getBirthDate()));
        return ResponseEntity.ok(candidateDto);
    }
}
