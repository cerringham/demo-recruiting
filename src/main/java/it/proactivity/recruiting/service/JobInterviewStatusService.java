package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.JobInterviewStatus;
import it.proactivity.recruiting.model.dto.JobInterviewStatusDto;
import it.proactivity.recruiting.repository.JobInterviewStatusRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.JobInterviewStatusUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobInterviewStatusService {

    @Autowired
    JobInterviewStatusRepository jobInterviewStatusRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    JobInterviewStatusUtility jobInterviewStatusUtility;

    public ResponseEntity<List<JobInterviewStatusDto>> getAll(String accessToken) {

        if (!jobInterviewStatusUtility.authorizeJobInterviewStatusService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<JobInterviewStatus> jobInterviewStatusList = jobInterviewStatusRepository.findByIsActive(true);

        List<JobInterviewStatusDto> dtoList = jobInterviewStatusList.stream()
                .map(j -> jobInterviewStatusUtility.createJobInterviewStatusDto(j.getName(), j.getDescription(), j.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<JobInterviewStatusDto> findById(String accessToken, Long id) {

        if (!jobInterviewStatusUtility.authorizeJobInterviewStatusService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<JobInterviewStatus> jobInterviewStatus = jobInterviewStatusRepository.findByIdAndIsActive(id, true);
        if (jobInterviewStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(jobInterviewStatusUtility.createJobInterviewStatusDto(jobInterviewStatus.get().getName(),
                jobInterviewStatus.get().getDescription(), jobInterviewStatus.get().getIsActive()));
    }
}
