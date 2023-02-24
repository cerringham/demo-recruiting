package it.proactivity.recruiting.utility;

import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ParsingUtility {

    public  String parseDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            String formattedDate = date.format(formatter);
            return formattedDate;
        } catch (DateTimeException e) {
            return null;
        }
    }
}
