package it.proactivity.recruiting.utility;

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

    public Boolean validateHour(String hour) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            LocalTime.parse(hour, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public Boolean validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public Boolean validatePlace(String place) {
        if (StringUtils.isEmpty(place)) {
            return false;
        }

        return StringUtils.isAlpha(place) && place.length() < PLACE_MAX_LENGTH;
    }

    public Boolean validateNote(String note) {
        if (StringUtils.isEmpty(note)) {
            return false;
        }

        return note.length() < NOTE_MAX_LENGTH;
    }

    public Boolean validateRating(String rating) {

        return StringUtils.isNumeric(rating);
    }
}
