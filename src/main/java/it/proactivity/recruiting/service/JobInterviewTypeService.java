package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.JobInterviewType;
import it.proactivity.recruiting.model.dto.JobInterviewTypeDto;
import it.proactivity.recruiting.repository.JobInterviewTypeRepository;
import it.proactivity.recruiting.utility.GlobalUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.JobInterviewTypeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JobInterviewTypeService {

    @Autowired
    JobInterviewTypeRepository jobInterviewTypeRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    JobInterviewTypeUtility jobInterviewTypeUtility;

    @Autowired
    GlobalUtility globalUtility;

    public ResponseEntity<List<JobInterviewTypeDto>> getAll(String accessToken) {
        Set<String> authorizedRoleNames = new HashSet<>();
        authorizedRoleNames.add("admin");
        authorizedRoleNames.add("hr");
        if (!globalUtility.checkIfTokenAndAccountAreValid(accessToken, authorizedRoleNames)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<JobInterviewType> jobInterviewTypeList = jobInterviewTypeRepository.findByIsActive(true);

        List<JobInterviewTypeDto> dtoList = jobInterviewTypeList.stream()
                .map(j -> jobInterviewTypeUtility.createJobInterviewTypeDto(j.getName()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<JobInterviewTypeDto> findById(String accessToken, Long id) {
        Set<String> authorizedRoleNames = new HashSet<>();
        authorizedRoleNames.add("admin");
        authorizedRoleNames.add("hr");
        if (!globalUtility.checkIfTokenAndAccountAreValid(accessToken, authorizedRoleNames)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        globalValidator.validateId(id);

        Optional<JobInterviewType> jobInterviewType = jobInterviewTypeRepository.findByIdAndIsActive(id, true);

        if (jobInterviewType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(jobInterviewTypeUtility.createJobInterviewTypeDto(jobInterviewType.get().getName()));
    }
}
