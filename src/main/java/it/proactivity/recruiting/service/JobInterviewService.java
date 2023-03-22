package it.proactivity.recruiting.service;


import it.proactivity.recruiting.model.JobInterview;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.repository.JobInterviewRepository;
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

    public ResponseEntity updateJobInterview(JobInterviewDto jobInterviewDto) {
        if (!jobInterviewUtility.validateJobInterviewParameters(jobInterviewDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<JobInterview> jobInterview = jobInterviewRepository.findById(jobInterviewDto.getId());
        if (jobInterview.isPresent()) {
            jobInterview.get().setDate(parsingUtility.parseStringToLocalDate(jobInterviewDto.getDate()));
            jobInterview.get().setHour(parsingUtility.parseStringToLocalTime(jobInterviewDto.getHour()));
            jobInterview.get().setEmployee(employeeUtility.getEmployeeFromId(jobInterviewDto.getEmployeeId()));
            jobInterview.get().setRating(jobInterviewDto.getRating());
            jobInterview.get().setNote(jobInterviewDto.getNote());
            jobInterviewRepository.save(jobInterview.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity deleteJobInterview(Long id) {
        if (!globalValidator.validateId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<JobInterview> jobInterview = jobInterviewRepository.findById(id);
        if (jobInterview.isPresent()) {
            jobInterview.get().setIsActive(false);
            jobInterviewRepository.save(jobInterview.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
