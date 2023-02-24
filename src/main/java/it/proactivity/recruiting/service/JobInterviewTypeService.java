package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.JobInterviewTypeDtoBuilder;
import it.proactivity.recruiting.model.JobInterviewType;
import it.proactivity.recruiting.model.dto.JobInterviewTypeDto;
import it.proactivity.recruiting.repository.JobInterviewTypeRepository;
import it.proactivity.recruiting.utility.JobInterviewTypeValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobInterviewTypeService {

    @Autowired
    JobInterviewTypeRepository jobInterviewTypeRepository;

    @Autowired
    JobInterviewTypeValidator jobInterviewTypeValidator;

    public ResponseEntity<List<JobInterviewTypeDto>> getAll() {
        List<JobInterviewType> jobInterviewTypeList = jobInterviewTypeRepository.findAll();

        List<JobInterviewTypeDto> dtoList = jobInterviewTypeList.stream()
                .map(j -> createJobInterviewTypeDto(j.getName()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<JobInterviewTypeDto> getById(Long id) {
        jobInterviewTypeValidator.validateId(id);

        Optional<JobInterviewType> jobInterviewType = jobInterviewTypeRepository.findById(id);

        if (jobInterviewType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(createJobInterviewTypeDto(jobInterviewType.get().getName()));

    }

    private JobInterviewTypeDto createJobInterviewTypeDto(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("The name can't be null or empty for the creation of JobInterviewTypeDto");
        }

        return JobInterviewTypeDtoBuilder.newBuilder(name).build();
    }
}
