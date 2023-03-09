package it.proactivity.recruiting.utility;

import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class ParsingUtility {

    public  String parseDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return date.format(formatter);
        } catch (DateTimeException e) {
            return null;
        }
    }

    public  String parseTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return time.format(formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public  LocalDate parseStringToLocalDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
