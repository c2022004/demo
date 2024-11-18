package com.example.projectsale.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
public class DateUtil {

    public static final long ONE_DAY = 1000 * 60 * 60 * 24;

    public static final int START_DATE_OF_MONTH = 1;

    public static final int MONTH_OF_YEAR = 12;

    public static final String FORMAT_YYYY_MMM = "yyyy-MMM";

    public static final String FORMAT_YYYY_MM = "yyyy-MM";

    public static final String FORMAT_YYYY = "yyyy";

    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String FORMAT_YYYYMM = "yyyyMM01";

    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_YYYY_MMM_DD = "yyyy-MMM-dd";

    public static final String FORMAT_DD_MM_YYYY = "dd-MM-yyyy";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static Date plusDateMonthYear(Date date, int option, int amount) {
        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (option) {
            case Calendar.DATE:
                cal.add(Calendar.DATE, amount);
                break;
            case Calendar.MONTH:
                cal.add(Calendar.MONTH, amount);
                break;
            case Calendar.YEAR:
                cal.add(Calendar.YEAR, amount);
                break;
            default:
                break;
        }
        return cal.getTime();
    }

    public static boolean isWeekend(Date date) {
        if (date == null) {
            return false;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
               cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static Date setDateOfMonth(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int compare(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return 0;
        }
        if (date1 == null) {
            return -1;
        }
        if (date2 == null) {
            return 1;
        }
        return date1.compareTo(date2);
    }

    public static Date stringToDate(String str) {
        return stringToDate(str, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    public static Date stringToDate(String str, String pattern, boolean isThrownIfError) throws ParseException {
        if (str.isEmpty() || pattern.isEmpty()) {
            return null;
        }
        Date date;
        SimpleDateFormat sim = new SimpleDateFormat(pattern);
        try {
            date = sim.parse(str);
        } catch (ParseException e) {
            date = null;

            if (isThrownIfError) {
                throw e;
            }
        }
        return date;
    }

    public static Date stringToDate(String str, String pattern) {
        Date date = null;
        try {
            date = stringToDate(str, pattern, false);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }

        return date;
    }

    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat sim = new SimpleDateFormat(pattern);
        return sim.format(date);
    }

    public static Date formatDate(Date date, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            String dateString = format.format(date);
            return format.parse(dateString);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static Date formatToEndDate(Date date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DateUtil.FORMAT_YYYY_MM_DD);
            String dateString = format.format(date);
            dateString += " 23:59:59";
            format.applyPattern(DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
            return format.parse(dateString);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static Date getStartDate(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    public static Date getStartDate(int year, int month, int date) {
        LocalDateTime localDate = LocalDateTime.of(year, month, date, 0, 0, 0, 0);
        return localDateTimeToDate(localDate);
    }

    public static Date getEndDate(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    public static Date getEndDate(int year, int month, int date) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, date, 23, 59, 59, 999999999);
        return localDateTimeToDate(localDateTime);
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date resetToFirstDateOfMonth(Date date) {
        if (date == null) {
            return null;
        }

        // Reset date of month to 1
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getStartDate(calendar.getTime());
    }

    public static Date resetToLastDateOfMonth(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getStartDate(calendar.getTime());
    }

    public static Date resetToEndDayOfLastDateOfMonth(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getEndDate(calendar.getTime());
    }

    public static List<Date> getMonthBetween(Date startMonth, Date endMonth, String pattern) {
        if (startMonth == null || endMonth == null) {
            return null;
        }

        List<Date> months = new ArrayList<>();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startMonth);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endMonth);

        for (Date date = startCalendar.getTime(); startCalendar.before(endCalendar);
             startCalendar.add(Calendar.MONTH, 1), date = startCalendar.getTime()) {
            Date formattedDate = DateUtil.formatDate(date, pattern);
            months.add(formattedDate);
        }

        return months;
    }

    public static List<Long> getDateOrMonthOrYearBetween(Date startDate, Date endDate, int option) {
        if (startDate == null || endDate == null) {
            return null;
        }

        List<Date> dateList = getMonthBetween(startDate, endDate, DateUtil.FORMAT_YYYY_MM);
        List<Long> results = new ArrayList<>();

        for (Date date : dateList) {
            if (!results.contains((long) getMonth(date))) {
                long number = 0;
                switch (option) {
                    case Calendar.DATE:
                        number = getDateOfMonth(date);
                        break;
                    case Calendar.MONTH:
                        number = getMonth(date);
                        break;
                    case Calendar.YEAR:
                        number = getYear(date);
                        break;
                    default:
                        break;
                }
                results.add(number);
            }
        }
        return results;
    }

    public static List<Date> getDaysBetweenDates(Date startDate, Date endDate, String pattern) {
        if (startDate == null || endDate == null) {
            return null;
        }

        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (calendar.getTime().before(plusDateMonthYear(endDate, Calendar.DATE, 1))) {
            Date date = calendar.getTime();
            Date formattedDate = DateUtil.formatDate(date, pattern);
            dates.add(formattedDate);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static List<String> getDaysBetweenMonths(Date startDate, Date endDate, String pattern) {
        if (startDate == null || endDate == null) {
            return null;
        }

        List<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(startDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date adjustedStartDate = calendar.getTime();

        calendar.setTime(endDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date adjustedEndDate = calendar.getTime();

        calendar.setTime(adjustedStartDate);

        while (calendar.getTime().before(adjustedEndDate) || calendar.getTime().equals(adjustedEndDate)) {
            dates.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }

        return dates;
    }

    public static List<String> getStringMonthBetween(Date startMonth, Date endMonth) {
        List<Date> dates = getMonthBetween(startMonth, endMonth, DateUtil.FORMAT_YYYY_MM);
        List<String> months = new ArrayList<>();

        if (!dates.isEmpty()) {
            for (Date date : dates) {
                months.add(dateToString(date, FORMAT_YYYY_MMM));
            }
        }

        return months;
    }

    public static int getAllDaysOfMonth(int month, int year) {
        return YearMonth.of(year, month).lengthOfMonth();
    }

    public static int getAllDaysOfMonth(Date date) {
        int month = getMonth(date);
        int year = getYear(date);
        return YearMonth.of(year, month).lengthOfMonth();
    }

    public static List<String> getStringDateInMonth(int month, int year, String pattern) {
        List<String> dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.YEAR, year);
        int daysInMonth = getAllDaysOfMonth(month, year);
        SimpleDateFormat df = new SimpleDateFormat(pattern);

        for (int i = 1; i <= daysInMonth; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i);
            dates.add(df.format(cal.getTime()));
        }

        return dates;
    }

    public static List<String> getStringDate(List<Date> dates, String pattern) {

        if (dates.isEmpty()) {
            return null;
        }

        List<String> strDates = new ArrayList<>();

        for (var date : dates) {
            if (date != null) {
                var strDate = dateToString(date, pattern);
                strDates.add(strDate);
            }
        }

        return strDates;
    }

    public static long countDayBetween(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return 0;
        }
        if (date1 == null || date2 == null) {
            return 1;
        }

        long dayCount = Math.round((double) (date1.getTime() - date2.getTime()) / ONE_DAY);
        return Math.abs(dayCount);
    }

    public static long getDayIntersectBetween(Date start1, Date end1, Date start2, Date end2) {
        long intersectCount = Math.max(0, Math.min(end1.getTime(), end2.getTime()) - Math.max(start1.getTime(), start2.getTime()));
        return Math.round((double) intersectCount / ONE_DAY);
    }

    public static Date getDate(int year, int month, int date) {
        LocalDate localDate = LocalDate.of(year, month, date);
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

}
