package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.JobPositionStatusDtoBuilder;
import it.proactivity.recruiting.model.JobPositionStatus;
import it.proactivity.recruiting.model.dto.JobPositionStatusDto;
import it.proactivity.recruiting.repository.JobPositionStatusRepository;
import it.proactivity.recruiting.utility.JobPositionStatusValidator;
import org.apache.commons.lang3.StringUtils;
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
    JobPositionStatusValidator jobPositionStatusValidator;
    public ResponseEntity<List<JobPositionStatusDto>> getAll() {

        List<JobPositionStatus> jobPositionStatusList = jobPositionStatusRepository.findAll();

        List<JobPositionStatusDto> dtoList = jobPositionStatusList.stream()
                .map(j -> createJobPositionStatusDto(j.getName(), j.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);

    }

    public ResponseEntity<JobPositionStatusDto> getById(Long id) {
        jobPositionStatusValidator.validateId(id);

        Optional<JobPositionStatus> jobPositionStatus = jobPositionStatusRepository.findById(id);
        if (jobPositionStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createJobPositionStatusDto(jobPositionStatus.get().getName(),
                jobPositionStatus.get().getIsActive()));
    }

    private JobPositionStatusDto createJobPositionStatusDto(String name, Boolean isActive) {
        if (StringUtils.isEmpty(name) || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of JobPositionStatusDto can't be null or empty");
        }

        return JobPositionStatusDtoBuilder.newBuilder(name)
                .isActive(isActive)
                .build();
    }
}
