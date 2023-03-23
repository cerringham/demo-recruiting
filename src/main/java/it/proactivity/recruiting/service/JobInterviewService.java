package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.*;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.model.dto.JobInterviewInsertionDto;
import it.proactivity.recruiting.model.dto.JobInterviewUpdateDto;
import it.proactivity.recruiting.repository.*;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.JobInterviewUtility;
import it.proactivity.recruiting.utility.JobInterviewValidator;
import it.proactivity.recruiting.utility.ParsingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobInterviewService {

    @Autowired
    JobInterviewRepository jobInterviewRepository;
    @Autowired
    GlobalValidator globalValidator;
    @Autowired
    ParsingUtility parsingUtility;
    @Autowired
    JobInterviewUtility jobInterviewUtility;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JobPositionRepository jobPositionRepository;
    @Autowired
    JobInterviewStatusRepository jobInterviewStatusRepository;
    @Autowired
    JobInterviewValidator jobInterviewValidator;


    private static final String NEW_INTERVIEW_STATUS = "New";


    public ResponseEntity<List<JobInterviewDto>> getAll() {
        List<JobInterview> jobInterviewList = jobInterviewRepository.findByIsActive(true);

        List<JobInterviewDto> dtoList = jobInterviewList.stream()
                .map(j -> jobInterviewUtility.createJobInterviewDto(parsingUtility.parseDateToString(j.getDate()),
                        parsingUtility.parseTimeToString(j.getHour()), j.getPlace(), j.getRating(), j.getNote(),
                        j.getIsActive())).toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<JobInterviewDto> findById(Long id) {
        globalValidator.validateId(id);
        Optional<JobInterview> jobInterview = jobInterviewRepository.findByIdAndIsActive(id, true);

        if (jobInterview.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(jobInterviewUtility.createJobInterviewDto(parsingUtility.parseDateToString(jobInterview.get().getDate()),
                parsingUtility.parseTimeToString(jobInterview.get().getHour()), jobInterview.get().getPlace(),
                jobInterview.get().getRating(), jobInterview.get().getNote(), jobInterview.get().getIsActive()));
    }

    public ResponseEntity createJobInterview(JobInterviewInsertionDto dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!jobInterviewValidator.validateDate(dto.getDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!jobInterviewValidator.validateHour(dto.getHour())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!jobInterviewValidator.validatePlace(dto.getPlace())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Candidate> candidate = candidateRepository.findById(dto.getCandidateId());
        Optional<Employee> employee = employeeRepository.findById(dto.getEmployeeId());
        Optional<JobPosition> jobPosition = jobPositionRepository.findById(dto.getJobPositionId());
        Optional<JobInterviewStatus> jobInterviewStatus = jobInterviewStatusRepository.findByName(dto.getJobInterviewStatus());

        if (candidate.isEmpty() || employee.isEmpty() || jobPosition.isEmpty() || jobInterviewStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<List<JobInterview>> candidateJobInterviewList = jobInterviewRepository.
                findByCandidateId(candidate.get().getId());

        JobInterview jobInterview;

        if (candidateJobInterviewList.isPresent() && candidateJobInterviewList.get().isEmpty()) {

            if (jobInterviewStatus.get().getName().equals(NEW_INTERVIEW_STATUS)) {

                try {
                    jobInterview = jobInterviewUtility.createJobInterview(candidate.get(), employee.get(),
                            jobPosition.get(), jobInterviewStatus.get(), dto.getHour(), dto.getDate(), dto.getPlace());
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
                }

                jobInterviewRepository.save(jobInterview);
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

        } else {
            if (candidateJobInterviewList.isPresent() &&
                    jobInterviewUtility.jobInterviewStatusNextStep(candidateJobInterviewList.get(), jobInterviewStatus.get())) {
                try {
                    jobInterview = jobInterviewUtility.createJobInterview(candidate.get(), employee.get(),
                            jobPosition.get(), jobInterviewStatus.get(), dto.getHour(), dto.getDate(), dto.getPlace());
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
                }

                jobInterviewRepository.save(jobInterview);
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
    }

    public ResponseEntity updateJobInterview(JobInterviewUpdateDto dto) {
        if (!jobInterviewValidator.validateHour(dto.getHour())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!jobInterviewValidator.validateDate(dto.getDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!jobInterviewValidator.validateNote(dto.getNote())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!jobInterviewValidator.validateRating(dto.getRating())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<JobInterview> jobInterview = jobInterviewRepository.findById(dto.getJobInterviewId());

        if (jobInterview.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!jobInterview.get().getIsActive()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Employee> employee = employeeRepository.findById(dto.getEmployeeId());

        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            jobInterviewUtility.updateParametersForJobInterview(jobInterview.get(), employee.get(), dto);
            jobInterviewRepository.save(jobInterview.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity deleteJobInterview(Long id) {
        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        jobInterviewRepository.deleteJobInterview(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
