package ru.fitnessmanager.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date getEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static boolean isBefore(Date date1, Date date2) {
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        Date newDate1 = c.getTime();
        c.setTime(date2);
        c.set(Calendar.HOUR_OF_DAY, 0);
        Date newDate2 = c.getTime();
        return newDate1.before(newDate2);
    }
}
