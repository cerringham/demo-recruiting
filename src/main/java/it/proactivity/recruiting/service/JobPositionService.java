package it.proactivity.recruiting.service;


import it.proactivity.recruiting.builder.JobPositionBuilder;
import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.JobPositionStatus;
import it.proactivity.recruiting.model.SkillLevel;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.model.dto.JobPositionWithSkillsDto;
import it.proactivity.recruiting.repository.CompanyRepository;
import it.proactivity.recruiting.repository.JobPositionRepository;
import it.proactivity.recruiting.repository.JobPositionStatusRepository;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.JobPositionUtility;
import it.proactivity.recruiting.utility.SkillLevelUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobPositionService {

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    JobPositionUtility jobPositionUtility;

    @Autowired
    JobPositionStatusRepository jobPositionStatusRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SkillLevelUtility skillLevelUtility;

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

    public ResponseEntity<JobPositionWithSkillsDto> insertJobPosition(JobPositionWithSkillsDto jobPositionWithSkillsDto) {
        if (!jobPositionUtility.validateParametersForInsert(jobPositionWithSkillsDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Company> company = companyRepository.findByName(jobPositionWithSkillsDto.getCompanyName());
        if (company.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<JobPositionStatus> jobPositionStatus = jobPositionStatusRepository.findByName(JobPosition.NEW_STATUS);
        List<SkillLevel> skillLevels = jobPositionWithSkillsDto.getSkillLevelDtos().stream()
                .map(s -> skillLevelUtility.createSkillLevelFromDto(s))
                .collect(Collectors.toList());
        JobPosition jobPosition = JobPositionBuilder.newBuilder(jobPositionWithSkillsDto.getTitle())
                .area(jobPositionWithSkillsDto.getArea())
                .description(jobPositionWithSkillsDto.getDescription())
                .city(jobPositionWithSkillsDto.getCity())
                .region(jobPositionWithSkillsDto.getRegion())
                .country(jobPositionWithSkillsDto.getCountry())
                .isActive(jobPositionWithSkillsDto.getIsActive())
                .company(company.get())
                .jobPositionStatus(jobPositionStatus.get())
                .skillList(skillLevels)
                .build();
        jobPositionRepository.save(jobPosition);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<JobPositionDto> updateJobPosition(Long id, String newStatus) {
        globalValidator.validateId(id);
        Optional<JobPosition> jobPosition = jobPositionRepository.findById(id);
        Optional<JobPositionStatus> jobPositionStatus = jobPositionStatusRepository.findByName(newStatus);
        if (jobPosition.isPresent()) {
            if (jobPositionStatus.isPresent()) {
                jobPosition.get().setJobPositionStatus(jobPositionStatus.get());
                jobPositionRepository.save(jobPosition.get());
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity deleteJobPosition(Long id) {
        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<JobPosition> jobPosition = jobPositionRepository.findById(id);
        if (jobPosition.isPresent()) {
            jobPositionRepository.inactivateJobPositionById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
