package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.JobPositionStatus;
import it.proactivity.recruiting.model.Skill;
import it.proactivity.recruiting.model.SkillLevel;
import it.proactivity.recruiting.model.dto.JobPositionInsertionDto;
import it.proactivity.recruiting.model.dto.JobPositionDto;
import it.proactivity.recruiting.myEnum.Level;
import it.proactivity.recruiting.repository.JobPositionRepository;
import it.proactivity.recruiting.repository.JobPositionStatusRepository;
import it.proactivity.recruiting.utility.GlobalUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.JobPositionUtility;
import it.proactivity.recruiting.utility.JobPositionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JobPositionService {

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    JobPositionUtility jobPositionUtility;

    @Autowired
    JobPositionValidator jobPositionValidator;

    @Autowired
    GlobalUtility globalUtility;
    @Autowired
    private JobPositionStatusRepository jobPositionStatusRepository;

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

    public ResponseEntity insertJobPosition(JobPositionInsertionDto dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //Check if map is null or skill are less than 2 and levels are valued
        if (!jobPositionValidator.validateSkillLevelMap(dto.getSkillLevelMap())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //Check  all string parameters
        if (!jobPositionUtility.validateAllStringParametersForJobPositionInsertionDto(dto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //save and insert new skills and creation of skill, level map
        Map<Skill, Level> skillLevelMap = globalUtility.insertNewSkillsAndReturnSkillLevelMap(dto.getSkillLevelMap());

        //Creation of job position
        JobPosition jobPosition;
        try {
            jobPosition = jobPositionUtility.createJobPosition(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        //Creation of skillLevelList to set on job position
        List<SkillLevel> skillLevelList = jobPositionUtility.createSkillList(skillLevelMap, jobPosition);

        jobPosition.setSkillList(skillLevelList);
        jobPositionRepository.save(jobPosition);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    public ResponseEntity updateJobPosition(JobPositionInsertionDto dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!jobPositionValidator.validateJobPositionStatusName(dto.getJobPositionStatusName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!globalValidator.validateId(dto.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<JobPositionStatus> jobPositionStatus = jobPositionStatusRepository
                .findByNameAndIsActive(dto.getJobPositionStatusName(), true);

        Optional<JobPosition> jobPosition = jobPositionRepository.findByIdAndIsActive(dto.getId(), true);

        if (jobPositionStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (jobPosition.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        jobPosition.get().setJobPositionStatus(jobPositionStatus.get());
        jobPositionRepository.save(jobPosition.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity deleteJobPosition(Long id) {
        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<JobPosition> jobPosition = jobPositionRepository.findById(id);
        if (jobPosition.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        jobPosition.get().setIsActive(false);
        jobPositionRepository.save(jobPosition.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
