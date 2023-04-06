package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.repository.CurriculumRepository;
import it.proactivity.recruiting.utility.CurriculumUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculumService {

    @Autowired
    CurriculumRepository curriculumRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    CurriculumUtility curriculumUtility;

    public ResponseEntity<List<CurriculumDto>> getAll(String accessToken) {

        if (!curriculumUtility.authorizeCurriculumService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Curriculum> curriculumListList = curriculumRepository.findAll();

        List<CurriculumDto> dtoList = curriculumListList.stream()
                .map(c -> curriculumUtility.createCurriculumDto(c.getCandidate().getId(), c.getCandidate().getName(),
                        c.getCandidate().getSurname(), c.getSkill().getName(), c.getLevel().toString()))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<CurriculumDto> findById(String accessToken, Long id) {
        if (!curriculumUtility.authorizeCurriculumService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Curriculum> curriculum = curriculumRepository.findById(id);

        if (curriculum.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(curriculumUtility.createCurriculumDto(curriculum.get().getCandidate().getId(),
                curriculum.get().getCandidate().getName(), curriculum.get().getCandidate().getSurname(),
                curriculum.get().getSkill().getName(), curriculum.get().getLevel().toString()));
    }
}
