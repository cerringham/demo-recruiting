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

    public ResponseEntity<List<JobInterviewDto>> getAll(String accessToken) {

        if (!jobInterviewUtility.authorizeJobInterviewService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<JobInterview> jobInterviewList = jobInterviewRepository.findByIsActive(true);

        List<JobInterviewDto> dtoList = jobInterviewList.stream()
                .map(j -> jobInterviewUtility.createJobInterviewDto(parsingUtility.parseDateToString(j.getDate()),
                        parsingUtility.parseTimeToString(j.getHour()), j.getPlace(), j.getRating(), j.getNote(),
                        j.getIsActive())).toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<JobInterviewDto> findById(String accessToken, Long id) {

        if (!jobInterviewUtility.authorizeJobInterviewService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<JobInterview> jobInterview = jobInterviewRepository.findByIdAndIsActive(id, true);

        if (jobInterview.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(jobInterviewUtility.createJobInterviewDto(parsingUtility.parseDateToString(jobInterview.get().getDate()),
                parsingUtility.parseTimeToString(jobInterview.get().getHour()), jobInterview.get().getPlace(),
                jobInterview.get().getRating(), jobInterview.get().getNote(), jobInterview.get().getIsActive()));
    }

    public ResponseEntity createJobInterview(String accessToken, JobInterviewInsertionDto dto) {

        if (!jobInterviewUtility.authorizeJobInterviewService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!jobInterviewValidator.validateAllParametersForJobInterviewInsertionDto(dto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Candidate> candidate = candidateRepository.findById(dto.getCandidateId());
        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<JobInterviewStatus> jobInterviewStatus = jobInterviewStatusRepository.findByName(dto.getJobInterviewStatus());

        if (jobInterviewStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<JobInterview> lastCandidateJobInterview = jobInterviewRepository.findFirstByCandidateOrderByIdDesc(candidate.get());

        JobInterview jobInterview;

        if (lastCandidateJobInterview.isEmpty()) {

            jobInterview = jobInterviewUtility.createNewJobInterview(candidate.get(), jobInterviewStatus.get(), dto);
        } else {
            jobInterview = jobInterviewUtility.createNextStepJobInterview(candidate.get(), jobInterviewStatus.get(),
                    lastCandidateJobInterview.get(), dto);
        }
        if (jobInterview == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        jobInterviewRepository.save(jobInterview);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity updateJobInterview(String accessToken, JobInterviewUpdateDto dto) {

        if (!jobInterviewUtility.authorizeJobInterviewService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!jobInterviewValidator.validateAllParametersForJobInterviewUpdateDto(dto)) {
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

    public ResponseEntity deleteJobInterview(String accessToken,Long id) {

        if (!jobInterviewUtility.authorizeJobInterviewService(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        jobInterviewRepository.deleteJobInterview(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
