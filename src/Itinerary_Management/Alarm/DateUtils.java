package Itinerary_Management.Alarm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date parseDateTime(String dateTime) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            return formatter.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }
}
