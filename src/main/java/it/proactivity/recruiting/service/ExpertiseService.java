package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.dto.ExpertiseDto;
import it.proactivity.recruiting.repository.ExpertiseRepository;
import it.proactivity.recruiting.utility.ExpertiseUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertiseService {

    @Autowired
    ExpertiseRepository expertiseRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    ExpertiseUtility expertiseUtility;


    public ResponseEntity<List<ExpertiseDto>> getAll() {

        List<Expertise> expertiseDtoList = expertiseRepository.findByIsActive(true);

        List<ExpertiseDto> dtoList = expertiseDtoList.stream()
                .map(c -> expertiseUtility.createExpertiseDto(c.getName(), c.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<ExpertiseDto> findById(Long id) {

        globalValidator.validateId(id);

        Optional<Expertise> expertise = expertiseRepository.findByIdAndIsActive(id, true);

        if (expertise.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(expertiseUtility.createExpertiseDto(expertise.get().getName(), expertise.get().getIsActive()));
    }
}

