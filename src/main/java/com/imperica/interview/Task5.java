package com.imperica.interview;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task5 {

    public static void findNumberOfWeek(int date, int month, int year) {

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month - 1, date);
        System.out.println("gregorianCalendar.getTime() = " + gregorianCalendar.getTime());
        int weeksInWeekYear = gregorianCalendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("Результат: " + weeksInWeekYear + "-й тиждень року");

    }

}
