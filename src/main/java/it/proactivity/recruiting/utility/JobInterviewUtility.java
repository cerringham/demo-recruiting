package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobInterviewBuilder;
import it.proactivity.recruiting.builder.JobInterviewDtoBuilder;
import it.proactivity.recruiting.model.*;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.model.dto.JobInterviewUpdateDto;
import it.proactivity.recruiting.repository.JobInterviewRepository;
import it.proactivity.recruiting.repository.JobInterviewStatusRepository;
import it.proactivity.recruiting.repository.JobInterviewTypeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class JobInterviewUtility {

    @Autowired
    ParsingUtility parsingUtility;

    @Autowired
    JobInterviewStatusRepository jobInterviewStatusRepository;

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

    public JobInterview createJobInterview(Candidate candidate, Employee employee, JobPosition jobPosition,
                                           JobInterviewStatus jobInterviewStatus, String hour, String date, String place) {

        LocalDate parsedDate = parsingUtility.parseStringToLocalDate(date);
        if (parsedDate == null) {
            throw new IllegalArgumentException("Impossible to parse the date");
        }

        LocalTime parsedTime = parsingUtility.parseStringToLocalTime(hour);
        if (parsedTime == null) {
            throw new IllegalArgumentException("Impossible to parse the time");
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
                .place(place)
                .jobInterviewStatus(jobInterviewStatus)
                .date(parsedDate)
                .hour(parsedTime)
                .employee(employee)
                .jobInterviewType(jobInterviewType)
                .isActive(true)
                .jobPosition(jobPosition)
                .build();
    }


    public Boolean jobInterviewStatusNextStep(List<JobInterview> jobInterviewList, JobInterviewStatus jobInterviewStatus) {

        List<JobInterviewStatus> jobInterviewStatusList = jobInterviewStatusRepository.findAll();

        List<JobInterviewStatus> candidateJobInterviewStatusList = jobInterviewList.stream()
                .map(JobInterview::getJobInterviewStatus)
                .toList();

        JobInterviewStatus nextStepStatus = jobInterviewStatusList.get(jobInterviewList.size());
        JobInterview lastCandidateJobInterview = jobInterviewList.get(jobInterviewList.size() - 1);


        if (lastCandidateJobInterview.getJobInterviewStatus().getName().equals(FAILED_INTERVIEW_STATUS) ||
                lastCandidateJobInterview.getJobInterviewStatus().getName().equals(SUCCESS_INTERVIEW_STATUS)) {
            return false;
        } else {
            if (!candidateJobInterviewStatusList.contains(nextStepStatus) &&
                    nextStepStatus.getName().equals(jobInterviewStatus.getName())) {

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
}
