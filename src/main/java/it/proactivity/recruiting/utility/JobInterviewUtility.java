package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobInterviewBuilder;
import it.proactivity.recruiting.model.*;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.repository.JobInterviewRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JobInterviewUtility {

    @Autowired
    GlobalValidator globalValidator;

    @Autowired
    ParsingUtility parsingUtility;

    @Autowired
    JobInterviewRepository jobInterviewRepository;

    public JobInterviewDto createJobInterviewDto(String date, String hour, String place, Integer rating, String note,
                                                 Boolean isActive) {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(hour) || StringUtils.isEmpty(note)
                || rating == null || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of job interview dto can't be null or empty");
        }
         JobInterviewDto jobInterviewDto = new JobInterviewDto(date, hour, place, rating, note, isActive);
        return jobInterviewDto;
    }

    public Boolean validateJobInterviewParameters(JobInterviewDto jobInterviewDto) {
        if (jobInterviewDto == null || parsingUtility.parseStringToLocalDate(jobInterviewDto.getDate()) == null ||
                parsingUtility.parseStringToLocalDate(jobInterviewDto.getHour()) == null ||
                !globalValidator.validateId(jobInterviewDto.getEmployeeId()) || jobInterviewDto.getRating() == null ||
                StringUtils.isEmpty(jobInterviewDto.getNote())) {
            return false;
        }
        return true;
    }

    public Boolean validateParametersForInsert(JobInterviewDto jobInterviewDto) {
        if (jobInterviewDto == null || parsingUtility.parseStringToLocalDate(jobInterviewDto.getDate()) == null ||
                parsingUtility.parseStringToLocalDate(jobInterviewDto.getHour()) == null ||
                jobInterviewDto.getEmployeeId() == null || jobInterviewDto.getCandidateId() == null ||
                StringUtils.isEmpty(jobInterviewDto.getPlace()) || jobInterviewDto.getJobPositionId() == null) {
            return false;
        }
        return true;
    }

    public JobInterview createJobInterview(JobInterviewDto jobInterviewDto, Candidate candidate, Employee employee,
                                           JobPosition jobPosition, JobInterviewStatus jobInterviewStatus) {
        return JobInterviewBuilder.newBuilder(parsingUtility.parseStringToLocalDate(jobInterviewDto.getDate()))
                .hour(parsingUtility.parseStringToLocalTime(jobInterviewDto.getHour()))
                .employeeId(employee)
                .candidateId(candidate)
                .place(jobInterviewDto.getPlace())
                .jobPositionId(jobPosition)
                .jobInterviewStatus(jobInterviewStatus)
                .isActive(true)
                .build();
    }

    public Integer getLastStatusFromList(List<JobInterview> jobInterviewList) {
        Optional<Integer> maxStatus = jobInterviewList.stream()
                .map( j -> j.getJobInterviewStatus().getSequenceOrder())
                .max(Integer::compare);
        return maxStatus.get();
    }

    public void setFalseOtherInterviews(List<JobInterview> jobInterviewList) {
        jobInterviewList.stream()
                .forEach(j -> j.setIsActive(false));
    }
}
