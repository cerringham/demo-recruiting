package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.CandidateDtoBuilder;
import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.dto.CandidateDto;
import it.proactivity.recruiting.repository.CandidateRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.ParsingUtility;
import org.apache.commons.lang3.StringUtils;
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

    public ResponseEntity<Set<CandidateDto>> getAll() {

        List<Candidate> candidateList = candidateRepository.findByIsActive(true);

        Set<CandidateDto> dtoList = candidateList.stream()
                .map(c -> createCandidateDto(c.getFiscalCode(), c.getName(), c.getSurname(), c.getCityOfBirth(),
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
        return ResponseEntity.ok(createCandidateDto(candidate.get().getFiscalCode(), candidate.get().getName(),
                candidate.get().getSurname(), candidate.get().getCityOfBirth(), candidate.get().getCountryOfBirth(),
                candidate.get().getCityOfResidence(), candidate.get().getStreetOfResidence(),
                candidate.get().getRegionOfResidence(), candidate.get().getCountryOfResidence(),
                candidate.get().getEmail(), candidate.get().getPhoneNumber(), candidate.get().getGender(),
                candidate.get().getIsActive(), parsingUtility.parseDateToString(candidate.get().getBirthDate())));
    }

    public ResponseEntity<CandidateDto> insertNewCandidate(CandidateDto candidateDto) {
        if (!globalValidator.validateFiscalCode(candidateDto.getFiscalCode()) ||
                !globalValidator.validateNameOrSurname(candidateDto.getName()) ||
                !globalValidator.validateNameOrSurname(candidateDto.getSurname()) ||
                !globalValidator.validateBirthDate(candidateDto.getBirthDate()) ||
                !globalValidator.validateNameOrSurname(candidateDto.getCityOfBirth()) ||
                !globalValidator.validateNameOrSurname(candidateDto.getCountryOfBirth()) ||
                !globalValidator.validateNameOrSurname(candidateDto.getCityOfResidence()) ||
                !globalValidator.validateNameOrSurname(candidateDto.getRegionOfResidence()) ||
                !globalValidator.validateEmail(candidateDto.getEmail()) ||
                globalValidator.validatePhoneNumber(candidateDto.getPhoneNumber())) {
            return ResponseEntity.badRequest().build();
        }

        Candidate candidate = createCandidateDto()
    }

    public ResponseEntity<CandidateDto> deleteById(Long id) {
        globalValidator.validateId(id);
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        candidate.get().setIsActive(false);
        candidateRepository.save(candidate.get());
        CandidateDto candidateDto = createCandidateDto(candidate.get().getName(), candidate.get().getFiscalCode(),
                candidate.get().getSurname(), candidate.get().getCityOfBirth(), candidate.get().getCountryOfBirth(),
                candidate.get().getCityOfResidence(), candidate.get().getStreetOfResidence(),
                candidate.get().getRegionOfResidence(), candidate.get().getCountryOfResidence(),
                candidate.get().getEmail(), candidate.get().getPhoneNumber(), candidate.get().getGender(),
                candidate.get().getIsActive(), parsingUtility.parseDateToString(candidate.get().getBirthDate()));
        return ResponseEntity.ok(candidateDto);
    }

    private CandidateDto createCandidateDto(String fiscalCode, String name, String surname, String cityOfBirth,
                                            String countryOfBirth, String cityOfResidence, String streetOfResidence,
                                            String regionOfResidence, String countryOfResidence, String email,
                                            String phoneNumber, String gender, Boolean isActive, String birthDate) {

        if (StringUtils.isEmpty(fiscalCode) || StringUtils.isEmpty(name) || StringUtils.isEmpty(surname) ||
                StringUtils.isEmpty(cityOfBirth) || StringUtils.isEmpty(countryOfBirth) ||
                StringUtils.isEmpty(cityOfResidence) || StringUtils.isEmpty(streetOfResidence) ||
                StringUtils.isEmpty(regionOfResidence) || StringUtils.isEmpty(countryOfResidence) ||
                StringUtils.isEmpty(email) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(gender) ||
                isActive == null || StringUtils.isEmpty(birthDate)) {

            throw new IllegalArgumentException("The data's for creating the candidate dto are empty or null");
        }

        return CandidateDtoBuilder.newBuilder(name)
                .fiscalCode(fiscalCode)
                .surname(surname)
                .cityOfBirth(cityOfBirth)
                .countryOfBirth(countryOfBirth)
                .cityOfResidence(cityOfResidence)
                .streetOfResidence(streetOfResidence)
                .regionOfResidence(regionOfResidence)
                .countryOfResidence(countryOfResidence)
                .email(email)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .isActive(isActive)
                .birthDate(birthDate)
                .build();
    }

    private Candidate createCandidateFromDto(CandidateDto candidateDto) {
        Candidate candidate = new Candidate();
        candidate.setFiscalCode(candidateDto.getFiscalCode());
        candidate.setName(candidateDto.getName());

    }
}
