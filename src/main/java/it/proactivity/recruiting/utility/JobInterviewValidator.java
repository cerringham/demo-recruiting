package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.dto.JobInterviewInsertionDto;
import it.proactivity.recruiting.model.dto.JobInterviewUpdateDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class JobInterviewValidator {

    private static final Integer PLACE_MAX_LENGTH = 20;

    private static final Integer NOTE_MAX_LENGTH = 250;

    public boolean validateAllParametersForJobInterviewInsertionDto(JobInterviewInsertionDto dto) {
        return dto != null && validateHour(dto.getHour()) && validateDate(dto.getDate()) && validatePlace(dto.getPlace());
    }

    public boolean validateAllParametersForJobInterviewUpdateDto(JobInterviewUpdateDto dto) {
        return dto != null && validateHour(dto.getHour()) && validateDate(dto.getDate()) && validateNote(dto.getNote()) &&
                validateRating(dto.getRating());
    }

    private Boolean validateHour(String hour) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            LocalTime.parse(hour, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private Boolean validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            return !LocalDate.parse(date, formatter).isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private Boolean validatePlace(String place) {
        if (StringUtils.isEmpty(place)) {
            return false;
        }

        return StringUtils.isAlpha(place) && place.length() < PLACE_MAX_LENGTH;
    }

    private Boolean validateNote(String note) {
        if (StringUtils.isEmpty(note)) {
            return false;
        }

        return note.length() < NOTE_MAX_LENGTH;
    }

    private Boolean validateRating(String rating) {

        return StringUtils.isNumeric(rating);
    }
}
