package dk.symptomtracker.symptomtracker_backend.Sevices;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateToDateTimeService {

    public LocalDateTime getDateTime (LocalDate date, String timeString){

        String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dateTimeString = dateString + " " + timeString;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        return dateTime;

    }
}
