package ledkis.ledkisbaseapp.util;

import android.content.Context;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

    public static final String FULL_DATE_SIMPLE_FORMAT = "dd-MM-yyy HH:mm";
    public static final String DATE_SIMPLE_FORMAT = "EEE HH:mm";
    public static final String NULL_ERROR_MESSAGE = "NULL";

    public static final int MILLIS_IN_ONE_DAY = 1000 * 60 * 60 * 24;
    public static final int MILLIS_IN_ONE_HOUR = 1000 * 60 * 60;
    public static final int MILLIS_IN_ONE_MIN = 1000 * 60;
    public static final int MILLIS_IN_ONE_SEC = 1000;

    public static boolean isSameDay(DateTime first, DateTime second) {
        return !(first == null || second == null) && first.getYear() == second.getYear() && first.getDayOfYear() == second.getDayOfYear();

    }

    public static boolean isBetween(DateTime date, Interval interval) {
        // http://stackoverflow.com/questions/883060/how-can-i-determine-if-a-date-is-between-two-dates-in-java
        return interval.getStart().compareTo(date) * date.compareTo(interval.getEnd()) > 0;
    }

    public static String getSimpleDateFormat(DateTime date) {
        return DateUtils.valueOrDefaultString(date, DATE_SIMPLE_FORMAT, NULL_ERROR_MESSAGE);
    }

    public static String getSimpleFullDateFormat(DateTime date) {
        return DateUtils.valueOrDefaultString(date, FULL_DATE_SIMPLE_FORMAT, NULL_ERROR_MESSAGE);
    }

    public static DateTime valueOrDefault(DateTime date, DateTime defaultDate) {
        return date != null ? date : defaultDate;
    }

    public static long valueOrDefault(DateTime date, long defaultDateMillis) {
        return date != null ? date.getMillis() : defaultDateMillis;
    }

    public static String valueOrDefaultString(DateTime date, String format, String defaultDateString) {
        return date != null ? date.toString(format) : defaultDateString;
    }

    public static String valueOrDefaultString(Date date, String format, String defaultDateString) {
        //new DateTime(null) return DateTime.now() !!
        return date != null ? new DateTime(date).toString(format) : defaultDateString;
    }

    public static boolean isDateExpired(DateTime date) {
        return DateTime.now().isAfter(date);
    }

    public static long getRemainingTimeUntilDate(DateTime date) {
        return date.getMillis() - DateTime.now().getMillis();
    }

    public static int getDays(long millis) {
        return (int) ((millis / (MILLIS_IN_ONE_DAY)) % 365);
    }

    public static int getHours(long millis) {
        return (int) ((millis / (MILLIS_IN_ONE_HOUR)) % 24);
    }

    public static int getMins(long millis) {
        return (int) ((millis / (MILLIS_IN_ONE_MIN)) % 60);
    }

    public static int getSecs(long millis) {
        return (int) ((millis / (MILLIS_IN_ONE_SEC)) % 60);
    }
}
