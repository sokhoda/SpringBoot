package infrastructure.date.converters;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleDateConverter {

    private static String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm";
    private static String DATE_PATTERN = "dd.MM.yyyy";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);


    public static String localDateTimeToString(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime stringToLocalDateTime(String dateTime) {
        return StringUtils.isEmpty(dateTime) ? null : LocalDateTime.parse(dateTime, dateTimeFormatter);
    }
}
