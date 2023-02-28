package it.proactivity.recruiting.utility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class ParsingUtility {

    public static String parseDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            String formattedDate = date.format(formatter);
            return formattedDate;
        } catch (DateTimeException e) {
            return null;
        }
    }

    public static String parseTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            String formattedTime = time.format(formatter);
            return formattedTime;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static LocalDate parseStringToDate(String date) {
        if (StringUtils.isEmpty(date)) {
            throw new IllegalArgumentException();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate parsed = LocalDate.parse(date, formatter);
            return parsed;
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
