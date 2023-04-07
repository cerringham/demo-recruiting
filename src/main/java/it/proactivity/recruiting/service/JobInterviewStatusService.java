package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.JobInterviewStatus;
import it.proactivity.recruiting.model.dto.JobInterviewStatusDto;
import it.proactivity.recruiting.repository.JobInterviewStatusRepository;
import it.proactivity.recruiting.utility.GlobalUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.JobInterviewStatusUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JobInterviewStatusService {

    @Autowired
    JobInterviewStatusRepository jobInterviewStatusRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    JobInterviewStatusUtility jobInterviewStatusUtility;

    @Autowired
    GlobalUtility globalUtility;

    public ResponseEntity<List<JobInterviewStatusDto>> getAll(String accessToken) {
        Set<String> authorizedRoleNames = new HashSet<>();
        authorizedRoleNames.add("admin");
        authorizedRoleNames.add("hr");
        if (!globalUtility.checkIfTokenAndAccountAreValid(accessToken, authorizedRoleNames)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<JobInterviewStatus> jobInterviewStatusList = jobInterviewStatusRepository.findByIsActive(true);

        List<JobInterviewStatusDto> dtoList = jobInterviewStatusList.stream()
                .map(j -> jobInterviewStatusUtility.createJobInterviewStatusDto(j.getName(), j.getDescription(), j.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<JobInterviewStatusDto> findById(String accessToken, Long id) {
        Set<String> authorizedRoleNames = new HashSet<>();
        authorizedRoleNames.add("admin");
        authorizedRoleNames.add("hr");
        if (!globalUtility.checkIfTokenAndAccountAreValid(accessToken, authorizedRoleNames)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        globalValidator.validateId(id);

        Optional<JobInterviewStatus> jobInterviewStatus = jobInterviewStatusRepository.findByIdAndIsActive(id, true);
        if (jobInterviewStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(jobInterviewStatusUtility.createJobInterviewStatusDto(jobInterviewStatus.get().getName(),
                jobInterviewStatus.get().getDescription(), jobInterviewStatus.get().getIsActive()));
    }
}
