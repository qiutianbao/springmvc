package com.bigdata.util;

import org.apache.commons.collections.keyvalue.DefaultKeyValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/***
 * 
 * @author Jameson
 *
 */
public abstract class DateUtil {
    private static Map<String, SimpleDateFormat> FORMATTER_CACHE = new HashMap<String, SimpleDateFormat>(10);
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_DAY_PATTERN = "MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String DATE_TIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;
    public static final String MAX_DATE_STR = "9999-12-31";

    private synchronized static SimpleDateFormat getFormatter(String pattern) {
        SimpleDateFormat formatter = FORMATTER_CACHE.get(pattern);
        if (formatter == null) {
            formatter = new SimpleDateFormat(pattern);
            FORMATTER_CACHE.put(pattern, formatter);
        }
        return formatter;
    }

    /**
     * 获取现在时间
     *
     * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String getStringDate() {
        return DateUtil.formatDate(DateUtil.getNow(), DATE_TIME_PATTERN);
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static final String getStringDateShort() {
        return DateUtil.formatDate(DateUtil.getNow(), DATE_PATTERN);
    }

    /**
     * 获取指定时间 小时:分;秒
     *
     * @param date 指定的时间
     * @return HH:mm:ss
     */
    public static final String getTimeShort(Date date) {
        return DateUtil.formatDate(date, TIME_PATTERN);
    }

    public static final String formatDate(Date date, String pattern) {
        if (date == null) return null;
        if (pattern == null) pattern = DATE_TIME_PATTERN;
        return DateUtil.getFormatter(pattern).format(date);
    }

    public static final Date parseDate(String strDate, String pattern) {
        if (strDate == null) return null;
        if (pattern == null) pattern = DATE_TIME_PATTERN;
        try {
            return DateUtil.getFormatter(pattern).parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将短时间格式字符串转换为时间 小时:分;秒 HH:mm:ss
     *
     * @param strDate HH:mm:ss
     * @return date
     */
    public static final Date strToTimeShort(String strDate) {
        return DateUtil.parseDate(strDate, TIME_PATTERN);
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static final Date strToDateLong(String strDate) {
        return DateUtil.parseDate(strDate, DATE_TIME_PATTERN);
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param date date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static final String dateToStrLong(Date date) {
        return DateUtil.formatDate(date, DATE_TIME_PATTERN);
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param date date
     * @return yyyy-MM-dd
     */
    public static final String dateToStr(Date date) {
        return DateUtil.formatDate(date, DATE_PATTERN);
    }

    /**
     * 将短时间格式时间转换为字符串HH:mm:ss
     *
     * @param date date
     * @return HH:mm:ss
     */
    public static final String timeToStr(Date date) {
        return DateUtil.formatDate(date, TIME_PATTERN);
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate strDate
     * @return yyyy-MM-dd
     */
    public static final Date strToDate(String strDate) {
        return DateUtil.parseDate(strDate, DATE_PATTERN);
    }

    public static final Date getMaxDate() {
        return strToDate(MAX_DATE_STR);
    }

    /**
     * 将短时间格式字符串转换为时间 HH:mm:ss
     *
     * @param strDate strDate
     * @return HH:mm:ss
     */
    public static final Date strToTime(String strDate) {
        return DateUtil.parseDate(strDate, TIME_PATTERN);
    }

    /**
     * 根据年月日,得到日期
     *
     * @param year
     * @param month      从0开始
     * @param dayOfMonth
     * @return
     */
    public static final Date getDate(int year, int month, int dayOfMonth) {
        Calendar retCalendar = new GregorianCalendar(year, month, dayOfMonth);
        return retCalendar.getTime();
    }


    /**
     * 得到指定日期的当月的最后一天
     *
     * @param date
     * @return
     */
    public static final Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.roll(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 得到现在时间
     *
     * @return currentDateTime
     */
    public static final Date getNow() {
        return new Date();
    }

    /**
     * 取得今天凌晨 YYYY-MM-DD 00:00:00
     *
     * @return
     */
    public static final Date getToday() {
        Calendar nowCalendar = new GregorianCalendar();
        nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
        nowCalendar.set(Calendar.MINUTE, 0);
        nowCalendar.set(Calendar.SECOND, 0);
        nowCalendar.set(Calendar.MILLISECOND, 0);
        return nowCalendar.getTime();
    }

    /**
     * 取得今日最后一秒时间 23:59:59
     *
     * @return
     */
    public static final Date getTodayMidNight() {
        Calendar nowCalendar = new GregorianCalendar();
        nowCalendar.set(Calendar.HOUR_OF_DAY, 23);
        nowCalendar.set(Calendar.MINUTE, 59);
        nowCalendar.set(Calendar.SECOND, 59);
        nowCalendar.set(Calendar.MILLISECOND, 999);
        return nowCalendar.getTime();
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static final String getPreTime(String sj1, String jj) {
        String result = "";
        try {
            Date date = DateUtil.parseDate(sj1, DATE_TIME_PATTERN);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, Integer.parseInt(jj));
            result = DateUtil.formatDate(cal.getTime(), DATE_TIME_PATTERN);
        } catch (Exception e) {
            //ignore this exception
        }
        return result;
    }

    /**
     * 得到当前时间的年份
     *
     * @return int year
     */
    public static final int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static final int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static final List<Date> getDateTwoDayList(Date startDate, Date endDate) {
        List<Date> list = new LinkedList<Date>();
        Date date = startDate;
        list.add(date);
        Calendar ca = Calendar.getInstance();
        while (date.getTime() < endDate.getTime()) {
            ca.setTime(date);
            ca.add(Calendar.DATE, 1);
            date = ca.getTime();
            list.add(date);
        }
        return list;
    }

    public static final int getWeekOfYearNumber(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setMinimalDaysInFirstWeek(1);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static final String getDayWeekDesc(Date date) {
        //todo i18n
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.MONDAY:
                return "星期一";
            case Calendar.TUESDAY:
                return "星期二";
            case Calendar.WEDNESDAY:
                return "星期三";
            case Calendar.THURSDAY:
                return "星期四";
            case Calendar.FRIDAY:
                return "星期五";
            case Calendar.SATURDAY:
                return "星期六";
            default:
                return "星期天";
        }
    }

    public static final int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static final int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static final int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    public static final int getWeeks(Date date1, Date date2) {
        Date start, end;
        if (date1.before(date2)) {
            start = date1;
            end = date2;
        } else {
            start = date2;
            end = date1;
        }
        start = DateUtil.parseDate(DateUtil.formatDate(start, DATE_PATTERN), DATE_PATTERN);
        end = DateUtil.parseDate(DateUtil.formatDate(end, DATE_PATTERN), DATE_PATTERN);
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        int weeks = 1;
        int days = 0;
        while (end.getTime() - start.getTime() >= 1000 * 3600 * 24) {
            c.add(Calendar.DATE, 1);
            days++;
            int weekDay = c.get(Calendar.DAY_OF_WEEK);
            if (Calendar.SUNDAY == weekDay && days > 1) weeks++;
            start = c.getTime();
        }
        return weeks;
    }

    /**
     * 计算两个时间之间的天数
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 相隔天数
     */
    public static final int getIntervalDays(Date start, Date end) {
        long s = start.getTime();
        long e = end.getTime();
        long interval = e - s >= 0 ? e - s : (-1) * (e - s);
        return (int) (interval / (1000 * 60 * 60 * 24)) + 1;
    }

    public static final List<DefaultKeyValue> getWeekRanges(Date start, Date end) {
        List<DefaultKeyValue> result = new ArrayList<DefaultKeyValue>();
        Calendar c = Calendar.getInstance();
        start = DateUtil.parseDate(DateUtil.formatDate(start, DateUtil.DATE_PATTERN), DateUtil.DATE_PATTERN);
        end = DateUtil.parseDate(DateUtil.formatDate(end, DateUtil.DATE_PATTERN), DateUtil.DATE_PATTERN);
        c.setTime(start);
        Date s = start;
        Date w = start;
        while (w.before(end)) {
            c.setTime(w);
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (week == Calendar.SUNDAY) {
                result.add(new DefaultKeyValue(s, w));
            }
            c.add(Calendar.DATE, 1);
            week = c.get(Calendar.DAY_OF_WEEK);
            w = c.getTime();
            if (week == Calendar.MONDAY) {
                s = w;
            }
        }
        c.setTime(w);
        if (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            result.add(new DefaultKeyValue(s, end));
        } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && s.before(w)) {
            result.add(new DefaultKeyValue(s, w));
        }
        return result;
    }

    public static final boolean isWorkday(Date date) {
        return !DateUtil.isWeekend(date);
    }

    public static final boolean isWeekend(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
        return (day == Calendar.SATURDAY || day == Calendar.SUNDAY);
    }

    public static final boolean dateEqual(Date date1, Date date2) {
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        int y1 = c.get(Calendar.YEAR);
        int m1 = c.get(Calendar.MONTH);
        int d1 = c.get(Calendar.DATE);
        c.setTime(date2);
        int y2 = c.get(Calendar.YEAR);
        int m2 = c.get(Calendar.MONTH);
        int d2 = c.get(Calendar.DATE);
        return (y1 == y2 && m1 == m2 && d1 == d2);
    }

    public static final boolean dateBefore(Date date1, Date date2) {
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date d1 = c.getTime();
        c.setTime(date2);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date d2 = c.getTime();
        return d1.before(d2);
    }

    public static final boolean dateBeforeOrEqual(Date date1, Date date2) {
        return (DateUtil.dateBefore(date1, date2) || DateUtil.dateEqual(date1, date2));
    }

    public static final String getLiveTimeInDay(Date time1, Date time2) {
        int s = DateUtil.getSecondsBetweenInDay(time1, time2);
        int h = s / 3600;
        int m = (s - 3600 * h) / 60;
        s = s - 60 * m;
        return h + "H" + m + "M" + s + "S";
    }

    public static final int getSecondsBetweenInDay(Date time1, Date time2) {
        int h1 = DateUtil.getHour(time1);
        int h2 = DateUtil.getHour(time2);
        int m1 = DateUtil.getMinute(time1);
        int m2 = DateUtil.getMinute(time2);
        int s1 = DateUtil.getSecond(time1);
        int s2 = DateUtil.getSecond(time2);
        if (h2 == 0 && m2 == 0 && s2 == 0) h2 = 24;
        int h = h2 - h1;
        int m = m2 - m1;
        int s = s2 - s1;
        s += m * 60;
        s += h * 3600;
        return s;
    }

    public static final int getMinutesBetween(Date time1, Date time2) {
        long s1 = time1.getTime();
        long s2 = time2.getTime();
        long gap = Math.abs(s1 - s2);
        return (int) (gap / 1000 / 60);
    }

    public static final boolean isGapInValidDate(Date sd1, Date ed1, Date sd2, Date ed2) {
        return ((DateUtil.dateBeforeOrEqual(sd1, sd2) && DateUtil.dateBeforeOrEqual(sd2, ed1)) ||
                (DateUtil.dateBeforeOrEqual(sd2, sd1) && DateUtil.dateBeforeOrEqual(ed1, ed2)) ||
                (DateUtil.dateBeforeOrEqual(sd1, ed2) && DateUtil.dateBeforeOrEqual(ed2, ed1)));
    }

    /**
     * 对日期+-天数
     *
     * @param date 原日期
     * @param day  天数(支持负数)
     * @return 新日期
     */
    public static final Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static final Date add(Date date, int field, int howMany) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, howMany);
        return calendar.getTime();
    }

    public static final boolean isInValidTime(Date ts1, Date te1, Date ts2, Date te2) {
        Calendar c = Calendar.getInstance();
        int sps = 0;
        int spe = 0;
        int bps = 0;
        int bpe = 0;
        c.setTime(ts1);
        sps += 3600 * c.get(Calendar.HOUR_OF_DAY);
        sps += 60 * c.get(Calendar.MINUTE);
        sps += c.get(Calendar.SECOND);
        c.setTime(ts2);
        bps += 3600 * c.get(Calendar.HOUR_OF_DAY);
        bps += 60 * c.get(Calendar.MINUTE);
        bps += c.get(Calendar.SECOND);
        c.setTime(te1);
        spe += 3600 * c.get(Calendar.HOUR_OF_DAY);
        if (spe == 0) spe = 3600 * 24;
        spe += 60 * c.get(Calendar.MINUTE);
        spe += c.get(Calendar.SECOND);
        c.setTime(te2);
        bpe += 3600 * c.get(Calendar.HOUR_OF_DAY);
        if (bpe == 0) bpe = 3600 * 24;
        bpe += 60 * c.get(Calendar.MINUTE);
        bpe += c.get(Calendar.SECOND);
        return (sps >= bps && spe <= bpe);
    }

    public static final boolean isGapInValidTime(Date ts1, Date te1, Date ts2, Date te2) {
        Calendar c = Calendar.getInstance();
        int sps = 0;
        int spe = 0;
        int bps = 0;
        int bpe = 0;
        c.setTime(ts1);
        sps += 3600 * c.get(Calendar.HOUR_OF_DAY);
        sps += 60 * c.get(Calendar.MINUTE);
        sps += c.get(Calendar.SECOND);
        c.setTime(ts2);
        bps += 3600 * c.get(Calendar.HOUR_OF_DAY);
        bps += 60 * c.get(Calendar.MINUTE);
        bps += c.get(Calendar.SECOND);
        c.setTime(te1);
        spe += 3600 * c.get(Calendar.HOUR_OF_DAY);
        if (spe == 0) spe = 3600 * 24;
        spe += 60 * c.get(Calendar.MINUTE);
        spe += c.get(Calendar.SECOND);
        c.setTime(te2);
        bpe += 3600 * c.get(Calendar.HOUR_OF_DAY);
        if (bpe == 0) bpe = 3600 * 24;
        bpe += 60 * c.get(Calendar.MINUTE);
        bpe += c.get(Calendar.SECOND);
        return ((sps <= bps && spe >= bps) || (sps >= bps && spe <= bpe) || (sps <= bpe && spe >= bpe));
    }

    public static final boolean timeBefore(Date time1, Date time2) {
        Calendar c = Calendar.getInstance();
        int sps = 0;
        int spe = 0;
        c.setTime(time1);
        sps += 3600 * c.get(Calendar.HOUR_OF_DAY);
        sps += 60 * c.get(Calendar.MINUTE);
        sps += c.get(Calendar.SECOND);
        c.setTime(time2);
        spe += 3600 * c.get(Calendar.HOUR_OF_DAY);
        spe += 60 * c.get(Calendar.MINUTE);
        spe += c.get(Calendar.SECOND);
        if (spe == 0) spe = 24 * 3600;
        return sps < spe;
    }

    public static final boolean timeEqual(Date time1, Date time2) {
        Calendar c = Calendar.getInstance();
        int sps = 0;
        int spe = 0;
        c.setTime(time1);
        sps += 3600 * c.get(Calendar.HOUR_OF_DAY);
        sps += 60 * c.get(Calendar.MINUTE);
        sps += c.get(Calendar.SECOND);
        c.setTime(time2);
        spe += 3600 * c.get(Calendar.HOUR_OF_DAY);
        spe += 60 * c.get(Calendar.MINUTE);
        spe += c.get(Calendar.SECOND);
        if (spe == 0) spe = 24 * 3600;
        return sps == spe;
    }

    public static final boolean timeBeforeOrEqual(Date time1, Date time2) {
        Calendar c = Calendar.getInstance();
        int sps = 0;
        int spe = 0;
        c.setTime(time1);
        sps += 3600 * c.get(Calendar.HOUR_OF_DAY);
        sps += 60 * c.get(Calendar.MINUTE);
        sps += c.get(Calendar.SECOND);
        c.setTime(time2);
        spe += 3600 * c.get(Calendar.HOUR_OF_DAY);
        spe += 60 * c.get(Calendar.MINUTE);
        spe += c.get(Calendar.SECOND);
        if (spe == 0) spe = 24 * 3600;
        return sps <= spe;
    }

    public static final Date getDateEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    public static final Date getDateStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static final Date getNextMonday(Date theDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(theDate);
        int day = c.get(Calendar.DAY_OF_WEEK);
        while (day != Calendar.MONDAY) {
            c.add(Calendar.DATE, 1);
            day = c.get(Calendar.DAY_OF_WEEK);
        }
        return theDate;
    }

    public static final Date getNextSaturday(Date theDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(theDate);
        int day = c.get(Calendar.DAY_OF_WEEK);
        while (day != Calendar.SATURDAY) {
            c.add(Calendar.DATE, 1);
            day = c.get(Calendar.DAY_OF_WEEK);
        }
        return theDate;
    }

    public static final Date getLastFriday(Date theDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(theDate);
        int day = c.get(Calendar.DAY_OF_WEEK);
        while (day != Calendar.FRIDAY) {
            c.add(Calendar.DATE, -1);
            day = c.get(Calendar.DAY_OF_WEEK);
        }
        return theDate;
    }

    public static final Date getLastSunday(Date theDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(theDate);
        int day = c.get(Calendar.DAY_OF_WEEK);
        while (day != Calendar.SUNDAY) {
            c.add(Calendar.DATE, -1);
            day = c.get(Calendar.DAY_OF_WEEK);
        }
        return theDate;
    }

    public static void main(String[] args) {
        System.out.println(getLastDayOfMonth(new Date()));
        Date s1 = DateUtil.parseDate("12:01:30", DateUtil.TIME_PATTERN);
        Date e1 = DateUtil.parseDate("12:03:00", DateUtil.TIME_PATTERN);
        Date s2 = DateUtil.parseDate("12:03:01", DateUtil.TIME_PATTERN);
        Date e2 = DateUtil.parseDate("14:00:00", DateUtil.TIME_PATTERN);
        System.out.println(DateUtil.isGapInValidTime(s1, e1, s2, e2));
    }
}