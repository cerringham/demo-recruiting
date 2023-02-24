package it.proactivity.recruiting.service;

import it.proactivity.recruiting.builder.JobInterviewBuilder;
import it.proactivity.recruiting.model.JobInterview;
import it.proactivity.recruiting.model.dto.JobInterviewDto;
import it.proactivity.recruiting.repository.JobInterviewRepository;
import it.proactivity.recruiting.utility.JobInterviewValidator;
import it.proactivity.recruiting.utility.ParsingUtility;
import org.apache.commons.lang3.StringUtils;
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
    JobInterviewValidator jobInterviewValidator;

    @Autowired
    ParsingUtility parsingUtility;

    public ResponseEntity<List<JobInterviewDto>> getAll() {
        List<JobInterview> jobInterviewList = jobInterviewRepository.findAll();

        List<JobInterviewDto> dtoList = jobInterviewList.stream()
                .map(j -> createJobInterviewDto(parsingUtility.parseDateToString(j.getDate()),
                        parsingUtility.parseTimeToString(j.getHour()), j.getPlace(), j.getRating(), j.getNote(),
                        j.getIsActive())).toList();

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<JobInterviewDto> getById(Long id) {
        jobInterviewValidator.validateId(id);
        Optional<JobInterview> jobInterview = jobInterviewRepository.findById(id);

        if (jobInterview.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(createJobInterviewDto(parsingUtility.parseDateToString(jobInterview.get().getDate()),
                parsingUtility.parseTimeToString(jobInterview.get().getHour()), jobInterview.get().getPlace(),
                jobInterview.get().getRating(), jobInterview.get().getNote(), jobInterview.get().getIsActive()));
    }

    private JobInterviewDto createJobInterviewDto(String date, String hour, String place, Integer rating, String note,
                                                  Boolean isActive) {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(hour) || StringUtils.isEmpty(place) || StringUtils.isEmpty(note)
                || rating == null || isActive == null) {
            throw new IllegalArgumentException("The parameters for the creation of job interview dto can't be null or empty");
        }
        return JobInterviewBuilder.newBuilder(date)
                .hour(hour)
                .place(place)
                .rating(rating)
                .note(note)
                .isActive(isActive)
                .build();
    }
}
