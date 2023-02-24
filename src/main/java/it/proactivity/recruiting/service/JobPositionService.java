package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.JobPositionDtoBuilder;
import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.repository.JobPositionRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import org.apache.commons.lang3.StringUtils;
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

    public ResponseEntity<List<JobPositionDto>> getAll() {
        List<JobPosition> jobPositionList = jobPositionRepository.findAll();

        List<JobPositionDto> dtoList = jobPositionList.stream()
                .map(j -> createJobPositionDto(j.getTitle(), j.getArea(), j.getDescription(), j.getCity(), j.getRegion(),
                        j.getCountry(), j.getIsActive())).toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<JobPositionDto> findById(Long id) {
        globalValidator.validateId(id);
        Optional<JobPosition> jobPosition = jobPositionRepository.findById(id);

        if (jobPosition.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(createJobPositionDto(jobPosition.get().getTitle(), jobPosition.get().getArea(),
                jobPosition.get().getDescription(), jobPosition.get().getCity(), jobPosition.get().getRegion(),
                jobPosition.get().getCountry(), jobPosition.get().getIsActive()));
    }

    private JobPositionDto createJobPositionDto(String title, String area, String description, String city, String region,
                                                String country, Boolean isActive) {

        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(area) || StringUtils.isEmpty(description) ||
                StringUtils.isEmpty(city) || StringUtils.isEmpty(region) || StringUtils.isEmpty(country) || isActive == null) {

            throw new IllegalArgumentException("The parameters for the creation of JobPositionDto can't be null or empty");
        }

        return JobPositionDtoBuilder.newBuilder(title)
                .area(area)
                .description(description)
                .city(city)
                .region(region)
                .country(country)
                .isActive(isActive)
                .build();
    }
}
