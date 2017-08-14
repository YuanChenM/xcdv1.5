/**
 * @screen core
 * @author ma_b
 */
package com.hoperun.core.utils;


import com.hoperun.core.consts.NumberConst;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * The utility class for date and time.
 */
public class DateTimeUtil {

    /** The Constant FORMAT_DATE_yymmddHHMM. */
    public static final String FORMAT_YYMMDD_HHMMSS = "yyMMddHHmmss";
    /** The Constant FORMAT_DATE_yyMMddHHmm. */
    public static final String FORMAT_YYMMDDHHMM = "yyMMddHHmm";
    /** The Constant FORMAT_DATE_YYYYMM. */
    public static final String FORMAT_YEAR_MONTH = "yyyyMM";
    /** The Constant FORMAT_DATE_YYYYMMDD. */
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    /** The Constant FORMAT_DATE_YY_MM. */
    public static final String FORMAT_YY_MM = "yyMM";
    /** The Constant FORMAT_MMM_YYYY. */
    public static final String FORMAT_MMMYYYY = "MMM yyyy";
    /** The Constant FORMAT_MMM_YYYY. */
    public static final String FORMAT_DDMMM = "dd MMM";
    /** The Constant FORMAT_MMM_YYYY. */
    public static final String FORMAT_MMM = "MMM";
    /** The Constant FORMAT_MMMYYYY. */
    public static final String FORMAT_DDMMMYYYY = "dd MMM yyyy";
    /** The Constant format for a date. */
    public static final String FORMAT_DATE_YYYYMMDD = "yyyy-MM-dd";
    /** The Constant FORMAT_MMMYYYY. */
    public static final String FORMAT_DDMMMYYYYHHMMSS = "dd MMM yyyy HH:mm:ss";
    /** The Constant FORMAT_DATE_DD. */
    public static final String FORMAT_DD = "dd";

    /** the pattern of dd-MMM-yyyy */
    public static final Pattern PATTERN_DATE = Pattern
        .compile("^([0-2]\\d|3[0,1])-\\b(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\b-(\\d{2}|\\d{4})$");

    /** the pattern of dd-MMM-yyyy */
    public static final Pattern PATTERN_DATE_DDMMYYYY = Pattern
        .compile("^([0-2]\\d|3[0,1])-([0-1]?\\d)-(\\d{2}|\\d{4})$");

    /** the pattern of dd-MMM-yyyy HH:mm:ss */
    public static final Pattern PATTERN_TIME = Pattern.compile(
        "^([0-2]\\d|3[0,1])-\\b(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\b-(\\d{2}|\\d{4}) ([0-1]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$");

    /** Daylight Saving Time Offset. */
    public static final int DAYLIGHT_SAVING_TIME_OFFSET = 3600000;

    /**
     * 
     * Datetime Type(MAX:yyyy-MM-dd 23:59:59.99999,MIN:yyyy-MM-dd 00:00:00.99999).
     * 
     */
    public static enum DatetimeType {

        /**
         * yyyy-MM-dd 23:59:59.99999
         */
        MAX,

        /**
         * yyyy-MM-dd 00:00:00.00000
         */
        MIN,

        /**
         * yyyy-MM-dd hh:mm:ss.SSSSS
         */
        CURRENT
    }

    /**
     * format Date.
     * 
     * @param targetFormat the target format string
     * @param originalFormat the original format string
     * @param date the date to format
     * @return formated date
     */
    public static String formatDate(String targetFormat, String originalFormat, String date) {
        if (date == null || date.toString().length() == 0) {
            return "";
        }

        if (targetFormat == null || targetFormat.length() == 0) {
            return date;
        }

        if (originalFormat == null || originalFormat.length() == 0) {
            return date;
        }

        String retDate = "";

        try {
            retDate = new SimpleDateFormat(targetFormat, Locale.US)
                .format(new SimpleDateFormat(originalFormat, Locale.US).parse(date.toString()));
        } catch (ParseException e) {
            retDate = "";
        }
        return retDate;
    }

    /**
     * format Date from default format(<code>DateTimeUtil.FORMAT_DDMMMYYYY</code>).
     * 
     * @param targetFormat the target format string
     * @param date the date to format
     * @return formated date
     */
    public static String formatDate(String targetFormat, Date date) {
        if (date == null || date.toString().length() == 0) {
            return "";
        }

        if (targetFormat == null || targetFormat.length() == 0) {
            return new SimpleDateFormat(DateTimeUtil.FORMAT_DDMMMYYYY, Locale.US).format(date);
        }
        return new SimpleDateFormat(targetFormat, Locale.US).format(date);
    }

    /**
     * convert date to timestamp.
     * 
     * 
     * @param date date
     * @param timpType date time Type
     * @return timestamp
     */
    public static Timestamp convertDateToTimestamp(java.sql.Date date, DatetimeType timpType) {

        if (date == null) {
            return null;
        }
        if (DatetimeType.CURRENT.equals(timpType)) {
            return new Timestamp(date.getTime());
        }

        String format = new SimpleDateFormat(FORMAT_DATE_YYYYMMDD).format(date);
        java.sql.Date newDate = java.sql.Date.valueOf(format);
        long newTime = newDate.getTime();
        if (DatetimeType.MAX.equals(timpType)) {
            newTime = newDate.getTime() + (NumberConst.IntDef.INT_THOUSAND * NumberConst.IntDef.INT_SIXTY
                    * NumberConst.IntDef.INT_SIXTY * NumberConst.IntDef.INT_TWENTY_FOUR - 1);
        }
        Timestamp timeStamp = new Timestamp(newTime);

        return timeStamp;
    }

    /**
     * Parse string to date by format.
     * 
     * @param dateString string
     * @param format format
     * @return date object
     */
    public static Date parseDate(String dateString, String format) {
        if (dateString == null) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(format, Locale.US);
            df.setLenient(false);
            // db2 will not allow year > 9999
            Date date = df.parse(dateString);
            if (isValidDate(date)) {
                return date;
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Is valid Date.
     * 
     * @param date the date
     * @return boolean true is valid date
     */
    public static boolean isValidDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        final int maxYear = 9999;
        final int minYear = 1900;
        if (calendar.get(Calendar.YEAR) > maxYear || calendar.get(Calendar.YEAR) < minYear) {

            return false;
        }
        return true;
    }

    /**
     * Convert timeZone to GMT+ format.
     * 
     * @param timezone time zone
     * @return the string
     */
    public static String convertTimezoneToGMT(String timezone) {

        if (StringUtil.isEmpty(timezone)) {
            return timezone;
        }

        String standardTimezone = timezone;
        if (StringUtil.isNumeric(standardTimezone)) {
            if (standardTimezone.indexOf('+') == 0) {
                standardTimezone = standardTimezone.substring(1);
            }

            int timezoneValue = Integer.valueOf(standardTimezone);
            int prefix = Math.abs(timezoneValue) / NumberConst.IntDef.INT_HUNDRED;
            if (timezoneValue / NumberConst.IntDef.INT_HUNDRED > NumberConst.IntDef.INT_TWELVE
                    || timezoneValue / NumberConst.IntDef.INT_HUNDRED < -NumberConst.IntDef.INT_THIRTEEN) {

                return null;
            }

            int remainder = Math.abs(timezoneValue) % NumberConst.IntDef.INT_HUNDRED;
            String remainderStr = remainder < NumberConst.IntDef.INT_TEN ? "0" + remainder : "" + remainder;
            standardTimezone = (timezoneValue > 0 ? "GMT+" : "GMT-") + prefix + ":" + remainderStr;
        }

        return standardTimezone;
    }

    /**
     * convert timestamp from the source time zone to the target time zone.
     * 
     * @param timestamp a timestamp
     * @param sourceTimeZone the source time zone
     * @param targetTimeZone the target time zone
     * @return the date that has converted
     */
    public static Timestamp convertTimezone(Timestamp timestamp, String sourceTimeZone, String targetTimeZone) {

        Long time = timestamp.getTime();
        Long sourceRelativelyGMT = time - TimeZone.getTimeZone(convertTimezoneToGMT(sourceTimeZone)).getRawOffset();
        Long targetTime = sourceRelativelyGMT
                + TimeZone.getTimeZone(convertTimezoneToGMT(targetTimeZone)).getRawOffset();

        Timestamp result = new Timestamp(targetTime);
        result.setNanos(timestamp.getNanos());
        return result;
    }

    /**
     * convert date from the source time zone to the target time zone.
     * 
     * @param dateTime a time
     * @param sourceTimeZone the source time zone
     * @param targetTimeZone the target time zone
     * @return the date that has converted
     */
    public static Date convertTimezone(Date dateTime, String sourceTimeZone, String targetTimeZone) {
        Long time = dateTime.getTime();
        Long sourceRelativelyGMT = time - TimeZone.getTimeZone(sourceTimeZone).getRawOffset();
        Long targetTime = sourceRelativelyGMT + TimeZone.getTimeZone(targetTimeZone).getRawOffset();
        return new Date(targetTime);
    }

    /**
     * <p>
     * Get the string specified the date at the "yyyyMM" format.
     * </p>
     * 
     * @param string string specified the date
     * @return String
     */
    public static String toNumDate(String string) {
        SimpleDateFormat strToDateFmt = new SimpleDateFormat("MMM-yyyy", Locale.US);
        ParsePosition pos = new ParsePosition(0);
        Date date = strToDateFmt.parse(string, pos);
        if (null == date) {
            return "";
        }
        SimpleDateFormat dateToStrFmt = new SimpleDateFormat("yyyyMM", Locale.US);
        return dateToStrFmt.format(date);
    }

    /**
     * <p>
     * Get the string specified the date.
     * </p>
     * 
     * @param string string specified the date
     * @param format the string specified the format
     * @return string of the date
     */
    public static String displayMonthDate(String string, String format) {
        SimpleDateFormat strToDateFmt = new SimpleDateFormat("yyyyMM", Locale.US);
        ParsePosition pos = new ParsePosition(0);
        Date date = strToDateFmt.parse(string, pos);
        if (null == date) {
            return "";
        }
        SimpleDateFormat dateToStrFmt = new SimpleDateFormat(format, Locale.US);
        return dateToStrFmt.format(date);
    }

    /**
     * 
     * <p>
     * To obtain a specified date, months or years after the specified.
     * </p>
     * 
     * @param yearMonth Specified date
     * @param dist The number of months
     * @return After computing the date
     */
    public static Date rollYearMonth(String yearMonth, int dist) {

        final String format = "yyyyMM";
        if ((yearMonth == null) || (yearMonth.length() != format.length())) {
            return null;
        }

        final Date date = parseDate(yearMonth, format);
        if (date == null) {
            return null;
        }
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, dist);

        return cal.getTime();
    }

    /**
     * <p>
     * get the first day of the month given by date
     * </p>
     * 
     * @param date date
     * @return the first day of the month given by date
     */
    public static Date firstDay(Date date) {

        Calendar curCal = Calendar.getInstance();
        curCal.setTime(date);
        curCal.set(Calendar.DATE, 1);
        return curCal.getTime();
    }

    /**
     * <p>
     * get the first day of the month.
     * </p>
     * 
     * @param date date
     * @return the last day of the month given by date
     */
    public static Date lastDay(Date date) {

        Calendar curCal = Calendar.getInstance();
        curCal.setTime(date);
        curCal.set(Calendar.DAY_OF_MONTH, 1);
        curCal.roll(Calendar.DAY_OF_MONTH, -1);
        return curCal.getTime();
    }

    /**
     * <p>
     * get the sum of the month between beginYm and endYm
     * </p>
     * 
     * @param beginYm the month begin
     * @param endYm the month end
     * @return months
     */
    public static int getDiffMonths(Date beginYm, Date endYm) {
        final Calendar begincal = Calendar.getInstance();
        begincal.setTime(beginYm);

        final Calendar endcal = Calendar.getInstance();
        endcal.setTime(endYm);

        int months = (endcal.get(Calendar.YEAR) - begincal.get(Calendar.YEAR)) * NumberConst.IntDef.INT_TWELVE
                + (endcal.get(Calendar.MONTH) - begincal.get(Calendar.MONTH));
        return months;
    }

    /**
     * 
     * <p>
     * Get difference between the number of days between two dates.
     * </p>
     * 
     * @param beginDate the date begin
     * @param endDate the date end
     * @return day
     */
    public static Long getDayDifferent(Date beginDate, Date endDate) {
        long day = 0;
        if (beginDate != null && endDate != null) {
            long beginTime = beginDate.getTime();
            long endTime = endDate.getTime();

            day = (endTime - beginTime) / (NumberConst.IntDef.INT_THOUSAND * NumberConst.IntDef.INT_TWENTY_FOUR
                    * NumberConst.IntDef.INT_SIXTY * NumberConst.IntDef.INT_SIXTY);
        }
        return day;
    }

    /**
     * Add special month to a source date.
     * 
     * @param srcDate source date
     * @param month the the year to add to add
     * @return the result date that added the parameter
     */
    public static Date addMonth(Date srcDate, int month) {
        return add(srcDate, 0, month, 0, 0, 0, 0, 0);
    }

    /**
     * Add special month to a source date.
     * 
     * @param srcDate source date
     * @param day the the year to add to add
     * @return the result date that added the parameter
     */
    public static Date addDay(Date srcDate, int day) {
        return add(srcDate, 0, 0, day, 0, 0, 0, 0);
    }

    /**
     * Add special year/month/day/hour/minute/second to a source date.
     * 
     * @param srcDate source date
     * @param year the year to add
     * @param month the the year to add to add
     * @param day the day to add
     * @param hour the hour to add
     * @param minute the minute to add
     * @param second the second to add
     * @return the result date that added the parameter
     */
    public static Date add(Date srcDate, int year, int month, int day, int hour, int minute, int second) {
        return add(srcDate, year, month, day, hour, minute, second, 0);
    }

    /**
     * Add special year/month/day/hour/minute/second to a source date.
     * 
     * @param srcDate source date
     * @param year the year to add
     * @param month the the year to add to add
     * @param day the day to add
     * @param hour the hour to add
     * @param minute the minute to add
     * @param second the second to add
     * @param milli the milliseconds to add
     * @return the result date that added the parameter
     */
    public static Date add(Date srcDate, int year, int month, int day, int hour, int minute, int second, int milli) {
        Date resultDate = null;

        if (srcDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(srcDate);
            if (year != 0) {
                calendar.add(Calendar.YEAR, year);
            }
            if (month != 0) {
                calendar.add(Calendar.MONTH, month);
            }
            if (day != 0) {
                calendar.add(Calendar.DATE, day);
            }
            if (hour != 0) {
                calendar.add(Calendar.HOUR, hour);
            }
            if (minute != 0) {
                calendar.add(Calendar.MINUTE, minute);
            }
            if (second != 0) {
                calendar.add(Calendar.SECOND, second);
            }
            if (milli != 0) {
                calendar.add(Calendar.MILLISECOND, milli);
            }
            resultDate = calendar.getTime();
        }

        return resultDate;
    }

    /**
     * Get day of week
     * 
     * @param srcDate date
     * @return day of week
     */
    public static int getDayOfWeek(Date srcDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(srcDate);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get day of week
     * 
     * @param srcDate date
     * @return day of week
     */
    public static int getWeekOfMonth(Date srcDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(srcDate);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * Get the first month of the year.
     * 
     * @param date date
     * @return the the first month of the year
     */
    public static Date getFirstMonthOfYear(Date date) {
        Calendar curCal = Calendar.getInstance();
        curCal.setTime(date);
        curCal.set(Calendar.MONTH, 0);
        curCal.set(Calendar.DAY_OF_MONTH, 1);
        return curCal.getTime();
    }

    /**
     * Get the last month of the year.
     * 
     * @param date date
     * @return the the first month of the year
     */
    public static Date getLastMonthOfYear(Date date) {

        Calendar curCal = Calendar.getInstance();
        curCal.setTime(date);
        curCal.set(Calendar.MONTH, NumberConst.IntDef.INT_ELEVEN);
        curCal.set(Calendar.DAY_OF_MONTH, 1);
        return curCal.getTime();
    }

    /**
     * month add N.
     * 
     * @param originalMonth the original month
     * @param n the month to add
     * @return originalMonth + N
     */
    public static String monthAddN(String originalMonth, int n) {
        final int lenMonth = 6;
        final int lenYear = 4;
        final int maxMonth = 12;
        if (StringUtil.isEmpty(originalMonth) || !StringUtil.isNumeric(originalMonth)
                || originalMonth.length() != lenMonth) {
            return null;
        }

        int year = Integer.valueOf(originalMonth.substring(0, lenYear));
        int month = Integer.valueOf(originalMonth.substring(lenYear));

        int addMonth = n;
        if (addMonth >= maxMonth) {
            year = year + addMonth / maxMonth;
            addMonth = addMonth % maxMonth;
        }

        String monthStr;
        if ((month + addMonth) > maxMonth) {
            year++;
            month = month + addMonth - maxMonth;
            monthStr = new DecimalFormat("00").format(Integer.valueOf(month));
        } else {
            month = month + addMonth;
            monthStr = new DecimalFormat("00").format(Integer.valueOf(month));
        }
        return new StringBuilder().append(year).append(monthStr).toString();
    }

    /**
     * get targetMonth
     * 
     * @param minuend format: yyyyMM
     * @param meiosis format: yyyyMM
     * @return minuendMonth - meiosisMonth
     */
    public static Integer monthSub(String minuend, String meiosis) {
        final int lenYearMonth = 6;
        final int lenYear = 4;
        final int monthInYear = 12;

        if (StringUtil.isEmpty(minuend) || StringUtil.isEmpty(meiosis)) {
            return null;
        }

        if (!StringUtil.isNumeric(minuend) || !StringUtil.isNumeric(meiosis)) {
            return null;
        }

        if (minuend.length() != lenYearMonth || meiosis.length() != lenYearMonth) {
            return null;
        }

        Integer minuendYear = Integer.valueOf(minuend.substring(0, lenYear));
        Integer minuendMonth = Integer.valueOf(minuend.substring(lenYear, lenYearMonth));
        Integer meiosisYear = Integer.valueOf(meiosis.substring(0, lenYear));
        Integer meiosisMonth = Integer.valueOf(meiosis.substring(lenYear, lenYearMonth));

        return (minuendYear - meiosisYear) * monthInYear + minuendMonth - meiosisMonth;
    }

    /**
     * get last month.
     * 
     * @param originalMonth the original month
     * @return last month
     */
    public static String getLastMonth(String originalMonth) {
        final int lenMonth = 6;
        final int lenYear = 4;
        final int maxMonth = 12;
        if (StringUtil.isEmpty(originalMonth) || !StringUtil.isNumeric(originalMonth)
                || originalMonth.length() != lenMonth) {
            return null;
        }

        int year = Integer.valueOf(originalMonth.substring(0, lenYear));
        int month = Integer.valueOf(originalMonth.substring(lenYear));

        if (month == 1) {
            year--;
            month = maxMonth;
        } else {
            month--;
        }

        String monthStr = new DecimalFormat("00").format(Integer.valueOf(month));
        return new StringBuilder().append(year).append(monthStr).toString();
    }

    /**
     * getCustomerDate
     * 
     * @return 当前时间
     */
    public static Date getCustomerDate() {
        return new Date();
    }

    /**
     * 获取日期年份
     * 
     * @param date date
     * @return 日期年份
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取日期月份
     * 
     * @param date date
     * @return 日期月份
     */
    public static int getMonthOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 获取日期号
     * 
     * @param date date
     * @return 日期号
     */
    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取月份起始日期
     * 
     * @param date date
     * @return 月份起始日期
     */
    public static Date getMinMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取月份最后日期
     * 
     * @param date date
     * @return 月份最后日期
     */
    public static Date getMaxMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
}
