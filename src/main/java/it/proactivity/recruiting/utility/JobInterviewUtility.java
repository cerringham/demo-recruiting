package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.JobInterviewBuilder;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobInterviewUtility {

    @Autowired
    GlobalValidator globalValidator;

    public JobInterviewDto createJobInterviewDto(String date, String hour, String place, Integer rating, String note,
                                                 Boolean isActive) {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(hour) || StringUtils.isEmpty(note)
                || rating == null || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of job interview dto can't be null or empty");
        }
        return JobInterviewBuilder.newBuilder(date)
                .hour(hour)
                .rating(rating)
                .note(note)
                .isActive(isActive)
                .build();
    }

    public Boolean validateJobInterviewParameters(JobInterviewDto jobInterviewDto) {
        if (jobInterviewDto.getDate() == null || jobInterviewDto.getHour() == null ||
                !globalValidator.validateId(jobInterviewDto.getEmployeeId()) || jobInterviewDto.getRating() == null ||
                StringUtils.isEmpty(jobInterviewDto.getNote())) {
            return false;
        }
        return true;
    }
}
