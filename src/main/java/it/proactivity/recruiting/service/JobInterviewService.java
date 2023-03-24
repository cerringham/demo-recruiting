package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.*;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.repository.*;
import it.proactivity.recruiting.utility.EmployeeUtility;
import it.proactivity.recruiting.utility.GlobalValidator;
import it.proactivity.recruiting.utility.JobInterviewUtility;
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
    EmployeeUtility employeeUtility;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Autowired
    JobInterviewStatusRepository jobInterviewStatusRepository;


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

    public ResponseEntity insertJobInterview(JobInterviewDto jobInterviewDto) {
        if (!jobInterviewUtility.validateParametersForInsert(jobInterviewDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Candidate> candidate = candidateRepository.findById(jobInterviewDto.getCandidateId());
        List<JobInterview> jobInterviewList = jobInterviewRepository.findByCandidateIdAndIsActive(jobInterviewDto.getCandidateId(), true);
        Optional<Employee> employee = employeeRepository.findById(jobInterviewDto.getEmployeeId());
        Optional<JobPosition> jobPosition = jobPositionRepository.findById(jobInterviewDto.getJobPositionId());
        Optional<JobInterviewStatus> jobInterviewStatus = jobInterviewStatusRepository.findByName("New");
        if (candidate.isEmpty() || employee.isEmpty() || jobPosition.isEmpty() || jobInterviewStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (jobInterviewList.isEmpty()) {
            JobInterview jobInterview = jobInterviewUtility.createJobInterview(jobInterviewDto, candidate.get(),
                    employee.get(), jobPosition.get(), jobInterviewStatus.get());
            jobInterviewRepository.save(jobInterview);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        Optional<JobInterviewStatus> jobInterviewStatus1 = jobInterviewList.stream()
                .map(j -> j.getJobInterviewStatus())
                .findAny();
        jobInterviewUtility.setFalseOtherInterviews(jobInterviewList);
        Optional<JobInterviewStatus> status =
                jobInterviewStatusRepository.findBySequenceOrder(jobInterviewStatus1.get().getSequenceOrder() + 1);
        JobInterview jobInterview = jobInterviewUtility.createJobInterview(jobInterviewDto, candidate.get(),
                employee.get(), jobPosition.get(), status.get());
        jobInterviewRepository.save(jobInterview);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity updateJobInterview(JobInterviewDto jobInterviewDto) {
        if (!jobInterviewUtility.validateJobInterviewParameters(jobInterviewDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<JobInterview> jobInterview = jobInterviewRepository.findById(jobInterviewDto.getId());
        if (!jobInterview.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        jobInterview.get().setDate(parsingUtility.parseStringToLocalDate(jobInterviewDto.getDate()));
        jobInterview.get().setHour(parsingUtility.parseStringToLocalTime(jobInterviewDto.getHour()));
        jobInterview.get().setEmployee(employeeUtility.getEmployeeFromId(jobInterviewDto.getEmployeeId()));
        jobInterview.get().setRating(jobInterviewDto.getRating());
        jobInterview.get().setNote(jobInterviewDto.getNote());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity deleteJobInterview(Long id) {
        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        int deleted = jobInterviewRepository.inactivateJobInterviewById(id);
        if (deleted == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
