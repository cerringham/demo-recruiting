package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.JobPositionStatus;
import it.proactivity.recruiting.model.dto.JobPositionStatusDto;
import it.proactivity.recruiting.repository.JobPositionStatusRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.JobPositionStatusUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPositionStatusService {

    @Autowired
    JobPositionStatusRepository jobPositionStatusRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    JobPositionStatusUtility jobPositionStatusUtility;

    public ResponseEntity<List<JobPositionStatusDto>> getAll(String accessToken) {

        if (!jobPositionStatusUtility.authorizeJobPositionStatusService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<JobPositionStatus> jobPositionStatusList = jobPositionStatusRepository.findByIsActive(true);

        List<JobPositionStatusDto> dtoList = jobPositionStatusList.stream()
                .map(j -> jobPositionStatusUtility.createJobPositionStatusDto(j.getName(), j.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);

    }

    public ResponseEntity<JobPositionStatusDto> findById(Long id, String accessToken) {

        if (!jobPositionStatusUtility.authorizeJobPositionStatusService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<JobPositionStatus> jobPositionStatus = jobPositionStatusRepository.findByIdAndIsActive(id, true);
        if (jobPositionStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(jobPositionStatusUtility.createJobPositionStatusDto(jobPositionStatus.get().getName(),
                jobPositionStatus.get().getIsActive()));
    }
}
