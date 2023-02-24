package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.JobInterviewStatusDtoBuilder;
import it.proactivity.recruiting.model.JobInterviewStatus;
import it.proactivity.recruiting.model.dto.JobInterviewStatusDto;
import it.proactivity.recruiting.repository.JobInterviewStatusRepository;
import it.proactivity.recruiting.utility.JobInterviewStatusValidator;
import org.apache.commons.lang3.StringUtils;
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
    JobInterviewStatusValidator jobInterviewStatusValidator;

    public ResponseEntity<List<JobInterviewStatusDto>> getAll() {

        List<JobInterviewStatus> jobInterviewStatusList = jobInterviewStatusRepository.findAll();

        List<JobInterviewStatusDto> dtoList = jobInterviewStatusList.stream()
                .map(j -> createJobInterviewStatusDto(j.getName(), j.getDescription(), j.getIsActive()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<JobInterviewStatusDto> getById(Long id) {
        jobInterviewStatusValidator.validateId(id);

        Optional<JobInterviewStatus> jobInterviewStatus = jobInterviewStatusRepository.findById(id);
        if (jobInterviewStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createJobInterviewStatusDto(jobInterviewStatus.get().getName(),
                jobInterviewStatus.get().getDescription(), jobInterviewStatus.get().getIsActive()));
    }

    private JobInterviewStatusDto createJobInterviewStatusDto(String name, String description, Boolean isActive) {

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(description) || isActive == null) {
            throw new IllegalArgumentException("the parameters for the creation of job interview status dto can't be " +
                    "null or empty");
        }
        return JobInterviewStatusDtoBuilder.newBuilder(name)
                .description(description)
                .isActive(isActive)
                .build();
    }
}
