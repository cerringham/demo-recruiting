package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.repository.JobPositionRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.JobPositionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPositionService {

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    JobPositionUtility jobPositionUtility;

    public ResponseEntity<List<JobPositionDto>> getAll() {
        List<JobPosition> jobPositionList = jobPositionRepository.findByIsActive(true);

        List<JobPositionDto> dtoList = jobPositionList.stream()
                .map(j -> jobPositionUtility.createJobPositionDto(j.getTitle(), j.getArea(), j.getDescription(), j.getCity(), j.getRegion(),
                        j.getCountry(), j.getIsActive())).toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<JobPositionDto> findById(Long id) {
        globalValidator.validateId(id);
        Optional<JobPosition> jobPosition = jobPositionRepository.findByIdAndIsActive(id, true);

        if (jobPosition.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(jobPositionUtility.createJobPositionDto(jobPosition.get().getTitle(), jobPosition.get().getArea(),
                jobPosition.get().getDescription(), jobPosition.get().getCity(), jobPosition.get().getRegion(),
                jobPosition.get().getCountry(), jobPosition.get().getIsActive()));
    }
}
