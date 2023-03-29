package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobInterviewBuilder;
import it.proactivity.recruiting.builder.JobInterviewDtoBuilder;
import it.proactivity.recruiting.model.*;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.model.dto.JobInterviewInsertionDto;
import it.proactivity.recruiting.model.dto.JobInterviewUpdateDto;
import it.proactivity.recruiting.repository.*;
import jakarta.persistence.NoResultException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Component
public class JobInterviewUtility {

    @Autowired
    ParsingUtility parsingUtility;

    @Autowired
    JobInterviewStatusRepository jobInterviewStatusRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JobPositionRepository jobPositionRepository;

    private static final String NEW_INTERVIEW_STATUS = "New";

    private static final String BEHAVIORAL_INTERVIEW_STATUS = "Behavioral";

    private static final String TECHNICAL_TO_DO_INTERVIEW_STATUS = "Technical to do";

    private static final String TECHNICAL_INTERVIEW_STATUS = "Technical";

    private static final String SUCCESS_INTERVIEW_STATUS = "Success";

    private static final String FAILED_INTERVIEW_STATUS = "Failed";

    @Autowired
    JobInterviewTypeRepository jobInterviewTypeRepository;
    @Autowired
    JobInterviewRepository jobInterviewRepository;

    public JobInterviewDto createJobInterviewDto(String date, String hour, String place, Integer rating, String note,
                                                 Boolean isActive) {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(hour) || StringUtils.isEmpty(place) || StringUtils.isEmpty(note)
                || rating == null || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of job interview dto can't be null or empty");
        }
        return JobInterviewDtoBuilder.newBuilder(date)
                .hour(hour)
                .place(place)
                .rating(rating)
                .note(note)
                .isActive(isActive)
                .build();
    }

    public JobInterview createJobInterview(Candidate candidate, JobInterviewStatus jobInterviewStatus, JobInterviewInsertionDto dto) {

        LocalDate parsedDate = parsingUtility.parseStringToLocalDate(dto.getDate());
        if (parsedDate == null) {
            throw new IllegalArgumentException("Impossible to parse the date");
        }

        LocalTime parsedTime = parsingUtility.parseStringToLocalTime(dto.getHour());
        if (parsedTime == null) {
            throw new IllegalArgumentException("Impossible to parse the time");
        }

        Optional<Employee> employee = employeeRepository.findById(dto.getEmployeeId());
        Optional<JobPosition> jobPosition = jobPositionRepository.findById(dto.getJobPositionId());

        if (employee.isEmpty()) {
            throw new NoResultException("Employee not found");
        }

        if (jobPosition.isEmpty()) {
            throw new NoResultException("JobPosition not found");
        }

        JobInterviewType jobInterviewType = null;

        if (jobInterviewStatus.getName().equals(NEW_INTERVIEW_STATUS) ||
                jobInterviewStatus.getName().equals(BEHAVIORAL_INTERVIEW_STATUS)) {

            jobInterviewType = jobInterviewTypeRepository.findBehavioral();
        }

        if (jobInterviewStatus.getName().equals(TECHNICAL_TO_DO_INTERVIEW_STATUS) ||
                jobInterviewStatus.getName().equals(TECHNICAL_INTERVIEW_STATUS)) {

            jobInterviewType = jobInterviewTypeRepository.findTechnical();
        }

        if (jobInterviewStatus.getName().equals(SUCCESS_INTERVIEW_STATUS)) {
            jobInterviewType = jobInterviewTypeRepository.findContractProposal();
        }

        return JobInterviewBuilder.newBuilder(candidate)
                .place(dto.getPlace())
                .jobInterviewStatus(jobInterviewStatus)
                .date(parsedDate)
                .hour(parsedTime)
                .employee(employee.get())
                .jobInterviewType(jobInterviewType)
                .isActive(true)
                .jobPosition(jobPosition.get())
                .build();
    }


    public Boolean jobInterviewStatusNextStep(JobInterviewStatus newJobInterviewStatus, JobInterview lastCandidateJobInterview) {


        if (lastCandidateJobInterview.getJobInterviewStatus().getName().equals(FAILED_INTERVIEW_STATUS) ||
                lastCandidateJobInterview.getJobInterviewStatus().getName().equals(SUCCESS_INTERVIEW_STATUS)) {
            return false;
        } else {

            Optional<JobInterviewStatus> nextStepStatus = jobInterviewStatusRepository.
                    findNextStepStatusBySequence(lastCandidateJobInterview.getJobInterviewStatus().getSequence());
            if (nextStepStatus.isEmpty()) {
                return false;
            }
            if (nextStepStatus.get().getName().equals(newJobInterviewStatus.getName()) || newJobInterviewStatus.getName().equals(FAILED_INTERVIEW_STATUS)) {

                lastCandidateJobInterview.setIsActive(false);
                jobInterviewRepository.save(lastCandidateJobInterview);
                return true;
            } else {
                return false;
            }
        }
    }


    public void updateParametersForJobInterview(JobInterview jobInterview, Employee employee, JobInterviewUpdateDto dto) {

        LocalDate parsedDate = parsingUtility.parseStringToLocalDate(dto.getDate());
        LocalTime parsedTime = parsingUtility.parseStringToLocalTime(dto.getHour());

        if (parsedDate == null) {
            throw new IllegalArgumentException("Impossible to parse the date");
        }

        if (parsedTime == null) {
            throw new IllegalArgumentException("Impossible to parse the time");
        }

        jobInterview.setDate(parsedDate);
        jobInterview.setHour(parsedTime);
        jobInterview.setEmployee(employee);
        jobInterview.setRating(Integer.parseInt(dto.getRating()));
        jobInterview.setNote(dto.getNote());
    }

    public JobInterview createNewJobInterview(Candidate candidate, JobInterviewStatus jobInterviewStatus,
                                              JobInterviewInsertionDto dto) {

        if (jobInterviewStatus.getName().equals(NEW_INTERVIEW_STATUS)) {

            try {
                return createJobInterview(candidate, jobInterviewStatus, dto);
            } catch (IllegalArgumentException | NoResultException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public JobInterview createNextStepJobInterview(Candidate candidate, JobInterviewStatus jobInterviewStatus,
                                                   JobInterview lastCandidateJobInterview, JobInterviewInsertionDto dto) {

        if (jobInterviewStatusNextStep(jobInterviewStatus, lastCandidateJobInterview)) {
            try {
                return createJobInterview(candidate, jobInterviewStatus, dto);
            } catch (IllegalArgumentException | NoResultException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
