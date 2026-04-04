package com.weng.system.common.utils;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 例如:2018-12-28
     */
    public static final String DATE = "yyyy-MM-dd";
    /**
     * 例如:2018-12
     */
    public static final String DATE_MONTH = "yyyy-MM";
    /**
     * 例如:2018
     */
    public static final String DATE_YEAR = "yyyy";
    /**
     * 例如:2018-12-28 17
     */
    public static final String DATE_HOUR= "yyyy-MM-dd HH";

    /**
     * 例如:2018-12-28
     */
    public static final String DATE_OTHER = "yyyy/MM/dd";

    /**
     * 例如:12月28日
     */
    public static final String DATE_DAY = "MM月dd日";

    /**
     * 例如:2018-12-28 10:00:00
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_START = "yyyy-MM-dd 00:00:00";

    public static final String DATE_TIME_END = "yyyy-MM-dd 23:59:59";

    public static final String MONTH_START = "-01 00:00:00";

    /**
     * 例如:2018/12/28 10:00:00
     */
    public static final String DATE_TIME_NEW = "yyyy/MM/dd HH:mm:ss";
    /**
     *
     */
    public static final String DATE_OTHER_TIME_START = "yyyy/MM/dd 00:00:00";

    public static final String START_DATE_HOURS = "yyyy-MM-dd HH:00:00";

    public static final String START_DATE_MINUTE = "yyyy-MM-dd HH:mm:00";

    public static final String END_DATE_MINUTE = "yyyy-MM-dd HH:mm:59";

    public static final String END_DATE_HOURS = "yyyy-MM-dd HH:59:59";

    public static final String DATE_ONE = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_TWO = "yyyyMMddHHmmss";

    public static final String DATE_THREE = "yyyyMMdd";

    public static final String NUM_ONE = " 00:00:00";

    public static final String NUM_TWO = "0000";

    public static final String NUM_THREE = " 23:59:59";

    private static final String NUM_FOUR = " 23:00:00";

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";
    /**
     * 例如:10
     */
    public static final String HOURS = "HH";
    /**
     * 例如:10日10时
     */
    public static final String TIME_CHARTS = "dd日HH时";

    /**
     * 例如:10日10时
     */
    public static final String TIME_CHARTS_SHOW = "MM.dd";

    /**
     * 例如:10月10日
     */
    public static final String TIME_CHARTS_DAY = "MM月dd日";

    /**
     * 例如:10月10日
     */
    public static final String TIME_CHARTS_DAY_ONE = "yyyy年MM月dd日";

    /**
     * 例如:10月10日
     */
    public static final String TIME_CHARTS_DAY_TWO = "yyyy年MM月dd日HH时";

    /**
     * 例如:2018-12-28 10:00:00
     */
    public static final String DATE_TIME_ONE = "yyyy年MM月dd日 HH:mm:ss";
    /**
     * 例如:10:00:00
     */
    public static final String TIME = "HHmmss";

    /**
     * 例如:10:00:00
     */
    public static final String TIME_ONE = "HH:mm:ss";
    /**
     * 例如:10:00
     */
    public static final String TIME_WITHOUT_SECOND = "HH:mm";

    /**
     * 例如:2018-12-28 10:00
     */
    public static final String DATE_TIME_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";

    // 时间元素
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String WEEK = "week";
    public static final String DAY = "day";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String SECOND = "second";
    // 根据指定格式显示日期和时间
    /**
     * yyyy-MM-dd
     */
    private static final DateTimeFormatter yyyyMMdd_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateUtil() {

    }

    /**
     * 获取1970到当前的秒数
     */
    public static long getTimeSecond() {
        return Instant.now().getEpochSecond();
    }

    /**
     * 获取1970到当前的毫秒数
     */
    public static long getTimeMillisecond() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static String convertTimeToString(Long time) {
        if (StringUtils.isEmpty(time)) {
            throw new RuntimeException("传入时间不能为空");
        }
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    /**
     * 将字符串转日期成Long类型的时间戳，格式为：yyyy-MM-dd HH:mm:ss
     */
    public static Long convertTimeToLong(String time) {
        if (StringUtils.isEmpty(time)) {
            throw new RuntimeException("传入时间不能为空");
        }
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式
     */
    public static String convertTimeToString(Long time, String format) {
        if (StringUtils.isEmpty(time)) {
            throw new RuntimeException("传入时间不能为空");
        }
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(format);
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    /**
     * 将字符串转日期成Long类型的时间戳，格式为自定义
     */
    public static Long convertTimeToLong(String time, String pattern) {
        if (StringUtils.isEmpty(time)) {
            throw new RuntimeException("传入时间不能为空");
        }
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 格式化日期为字符串
     *
     * @param date    date
     * @param pattern 格式
     *
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化日期为字符串
     *
     * @param date date
     *
     * @return 日期字符串
     */
    public static String dateToString(Date date) {
        return dateToString(date, DATE);
    }

    /**
     * 格式化日期为字符串
     *
     * @param date    date
     * @param pattern 格式
     *
     * @return 日期字符串
     */
    public static String dateToString(Date date, String pattern) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String formatCstTime(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CST_TIME_PATTERN, Locale.US);
        Date usDate = simpleDateFormat.parse(date);
        return DateUtil.getDateFormat(usDate, format);
    }

    public static String formatInstant(Instant instant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 解析字符串日期为Date
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     *
     * @return Date
     */
    public static Date parse(String dateStr, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 解析字符串日期为Date
     *
     * @param dateStr 日期字符串
     *
     * @return Date
     */
    public static Date parseCommonDate(String dateStr) {
        LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE));
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 为Date增加分钟,减传负数
     *
     * @param date        日期
     * @param plusMinutes 要增加的分钟数
     *
     * @return 新的日期
     */
    public static Date addMinutes(Date date, Long plusMinutes) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime newDateTime = dateTime.plusMinutes(plusMinutes);
        return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 增加时间,减传负数
     *
     * @param date date
     * @param hour 要增加的小时数
     *
     * @return new date
     */
    public static Date addHour(Date date, Long hour) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusHours(hour);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 增加时间,减传负数
     *
     * @param date date
     * @param days 要增加的天数
     *
     * @return new date
     */
    public static Date addDay(Date date, Long days) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusDays(days);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 增加时间,减传负数
     *
     * @param date  date
     * @param month 要增加的月数
     *
     * @return new date
     */
    public static Date addMonth(Date date, Long month) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusMonths(month);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 增加时间,减传负数
     *
     * @param date date
     * @param year 要增加的月数
     *
     * @return new date
     */
    public static Date addYear(Date date, Long year) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusYears(year);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @return 返回当天的起始时间
     */
    public static Date getStartTime() {
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        return localDateTime2Date(now);
    }

    /**
     * @return 返回当天的结束时间
     */
    public static Date getEndTime() {
        LocalDateTime now = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
        return localDateTime2Date(now);
    }

    /**
     * 减月份
     *
     * @param monthsToSubtract 月份
     *
     * @return Date
     */
    public static Date minusMonths(long monthsToSubtract) {
        LocalDate localDate = LocalDate.now().minusMonths(monthsToSubtract);
        return localDate2Date(localDate);
    }

    /**
     * 减年份
     *
     * @param yearsToSubtract 年份
     *
     * @return Date
     */
    public static Date minusYears(long yearsToSubtract) {
        LocalDate localDate = LocalDate.now().minusYears(yearsToSubtract);
        return localDate2Date(localDate);
    }

    /**
     * LocalDate类型转为Date
     *
     * @param localDate LocalDate object
     *
     * @return Date object
     */
    public static Date localDate2Date(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDateTime类型转为Date
     *
     * @param localDateTime LocalDateTime object
     *
     * @return Date object
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 查询当前年的第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     *
     * @return 20190101
     */
    public static String getFirstDayOfCurrentYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().withMonth(1).withDayOfMonth(1);

        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询年的第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     *
     * @return 20190101
     */
    public static String getFirstDayOfYear(String time, String pattern) {
        Date date = parse(time.substring(0,10) + NUM_ONE, DATE_TIME);
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.withMonth(1).withDayOfMonth(1);

        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     *
     * @return 20190101
     */
    public static String getLastMonthFirstDayOfPreviousYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1L).withMonth(12).withDayOfMonth(1);

        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询当前月的第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     *
     * @return 20190101
     */
    public static String getFirstDayOfCurrentMonth(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMonths(0).with(TemporalAdjusters.firstDayOfMonth());

        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询月的第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     *
     * @return 20190101
     */
    public static String getFirstDayOfMonth(String time, String pattern) {
        Date date = parse(time.substring(0,10) + NUM_ONE, DATE_TIME);
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusMonths(0).with(TemporalAdjusters.firstDayOfMonth());

        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询当前周的第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     *
     * @return 20190101
     */
    public static String getFirstDayOfCurrentWeek(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMonths(0).with(DayOfWeek.MONDAY);

        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询当前周的最后一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     *
     * @return 20190101
     */
    public static String getLastDayOfCurrentWeek(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMonths(0).with(DayOfWeek.SUNDAY);

        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     *
     * @return 20190101
     */
    public static String getLastMonthLastDayOfPreviousYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1L).with(TemporalAdjusters.lastDayOfYear());
        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 获取当前日期
     *
     * @param pattern 格式，默认格式yyyyMMdd
     *
     * @return 20190101
     */
    public static String getCurrentDay(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();

        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 获取当前日期的小时
     *
     * @param pattern 格式，默认格式yyyyMMdd
     *
     * @return 20190101
     */
    public static String getCurrentHours(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();

        if (StringUtils.isEmpty(pattern)) {
            pattern = START_DATE_HOURS;
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 获取年
     *
     * @return 年
     */
    public static int getYear() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.YEAR);
    }

    /**
     * @return
     */
    public static int getTime() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.HOUR_OF_DAY);
    }

    /**
     * 获取月份
     *
     * @return 月份
     */
    public static int getMonth() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.MONTH_OF_YEAR);
    }

    /**
     * 获取某月的第几天
     *
     * @return 几号
     */
    public static int getMonthOfDay() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.DAY_OF_MONTH);
    }

    private static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(date);
    }

    /**
     * @param time 时间
     * @param num  加的数，-num就是减去
     *
     * @return 减去相应的数量的天的日期
     */
    public static Date dayAddNum(Date time, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        return calendar.getTime();
    }

    /**
     * @param time 时间
     * @param num  加的数，-num就是减去
     *
     * @return 减去相应的数量的天的日期
     */
    public static Date monthAddNum(Date time, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MONTH, num);
        return calendar.getTime();
    }

    /**
     * 获取时间间隔(小时)
     *
     * @param dateNow
     * @param dateOld
     *
     * @return
     */
    public static int getDateIntervalHour(Date dateNow, Date dateOld) {
        Instant now = dateNow.toInstant();
        Instant old = dateOld.toInstant();
        return Integer.parseInt(Duration.between(now, old).toHours() + "");
    }

    /**
     * 获取时间间隔(天)
     *
     * @param dateNow
     * @param dateOld
     *
     * @return
     */
    public static int getDateIntervalDay(Date dateNow, Date dateOld) {
        Instant now = dateNow.toInstant();
        Instant old = dateOld.toInstant();
        return Integer.parseInt(Duration.between(now, old).toDays() + "");
    }

    /**
     * 获取时间间隔(分钟)
     *
     * @param dateNow
     * @param dateOld
     *
     * @return
     */
    public static int getDateIntervalMinute(Date dateNow, Date dateOld) {
        Instant now = dateNow.toInstant();
        Instant old = dateOld.toInstant();
        return Integer.parseInt(Duration.between(now, old).toMinutes() + "");
    }

    /**
     * 检测：输入年份是否是闰年？
     *
     * @param date 日期格式：yyyy-MM-dd
     *
     * @return true：闰年，false：平年
     */
    public static boolean isLeapYear(String date) {
        return LocalDate.parse(date.trim()).isLeapYear();
    }

    /**
     * 切割日期。按照周期切割成小段日期段。例如： <br>
     *
     * @param startDate 开始日期（yyyy-MM-dd）
     * @param endDate   结束日期（yyyy-MM-dd）
     * @param period    周期（天，周，月，年）
     *
     * @return 切割之后的日期集合
     * @example <li>startDate="2019-02-28",endDate="2019-03-05",period="day"
     * </li>
     * <li>结果为：[2019-02-28, 2019-03-01, 2019-03-02, 2019-03-03,
     * 2019-03-04, 2019-03-05]</li><br>
     * <li>startDate="2019-02-28",endDate="2019-03-25",period="week"
     * </li>
     * <li>结果为：[2019-02-28,2019-03-06, 2019-03-07,2019-03-13,
     * 2019-03-14,2019-03-20, 2019-03-21,2019-03-25]</li><br>
     * <li>startDate="2019-02-28",endDate="2019-05-25",period="month"
     * </li>
     * <li>结果为：[2019-02-28,2019-02-28, 2019-03-01,2019-03-31,
     * 2019-04-01,2019-04-30, 2019-05-01,2019-05-25]</li><br>
     * <li>startDate="2019-02-28",endDate="2020-05-25",period="year"
     * </li>
     * <li>结果为：[2019-02-28,2019-12-31, 2020-01-01,2020-05-25]</li><br>
     */
    public static List<String> getPieDateRange(String startDate, String endDate, String period) {
        List<String> result = Lists.newArrayList();
        LocalDate end = LocalDate.parse(endDate, yyyyMMdd_EN);
        LocalDate start = LocalDate.parse(startDate, yyyyMMdd_EN);
        LocalDate tmp = start;
        switch (period) {
            case DAY:
                while (start.isBefore(end) || start.isEqual(end)) {
                    result.add(start.toString());
                    start = start.plusDays(1);
                }
                break;
            case WEEK:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    if (tmp.plusDays(6).isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + tmp.plusDays(6));
                    }
                    tmp = tmp.plusDays(7);
                }
                break;
            case MONTH:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfMonth = tmp.with(TemporalAdjusters.lastDayOfMonth());
                    if (lastDayOfMonth.isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + lastDayOfMonth);
                    }
                    tmp = lastDayOfMonth.plusDays(1);
                }
                break;
            case YEAR:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfYear = tmp.with(TemporalAdjusters.lastDayOfYear());
                    if (lastDayOfYear.isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + lastDayOfYear);
                    }
                    tmp = lastDayOfYear.plusDays(1);
                }
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 获取当天开始->结束时间，传入天数的前/后N天小时集合，含当天，负数为前N天
     *
     * @param days
     *
     * @return
     */
    public static List<String> getCurrentDayHourDateRange(Integer days) {
        List<String> list = new ArrayList<>();
        if (days > 0) {
            for (int i = 0; i < days * 24; i++) {
                String tmp = getDateFormat(addHour(getStartTime(), (long) i), DATE_TIME);
                list.add(tmp);
            }
        } else {
            // System.out.println( days * 24);
            Long i = (long) (days * 24);
            while (i < 24) {
                // System.out.println( days * 24);
                // System.out.println( i * 24);
                String tmp = getDateFormat(addHour(getStartTime(), i), DATE_TIME);
                list.add(tmp);
                i++;
            }
        }
        return list;
    }

    /**
     * 获取当天开始->结束时间，传入天小时前小时集合，含当天，负数为前N小时
     *
     * @param hours
     *
     * @return
     */
    public static List<String> getCurrentHourRange(Integer hours) {
        List<String> list = new ArrayList<>();
        if (hours >= 0) {
            for (int i = 0; i <= hours; i++) {
                String tmp = getDateFormat(addHour(new Date(), (long) i), START_DATE_HOURS);
                list.add(tmp);
            }
        } else {
            for (int i = hours; i <= 0; i++) {
                String tmp = getDateFormat(addHour(new Date(), (long) i), START_DATE_HOURS);
                list.add(tmp);
            }
        }
        return list;
    }

    /**
     * 获取开始时间(小)到结束时间（大）内的小时集合
     *
     * @param startTime
     * @param endTime
     *
     * @return
     */
    public static List<String> getCurrentHourRange(Date startTime, Date endTime) {
        List<String> list = new ArrayList<>();
        list.add(getDateFormat(startTime, START_DATE_HOURS));
        int i = 1;
        while (true) {
            Date date = addHour(startTime, (long) i);
            if (date.getTime() > endTime.getTime()) {
                // list.add(getDateFormat(date, START_DATE_HOURS));
                break;
            }
            i++;
            list.add(getDateFormat(date, START_DATE_HOURS));
        }
        return list;
    }

    /**
     * 获取开始时间（小）到结束时间（大）内的天集合
     *
     * @param startTime
     * @param endTime
     *
     * @return
     */
    public static List<String> getCurrentDayRange(Date startTime, Date endTime) {
        List<String> list = new ArrayList<>();
        list.add(getDateFormat(startTime, DATE));
        int i = 1;
        while (true) {
            Date date = addDay(startTime, (long) i);
            Date dayStartTime = getDayStartTime(date);
            if (dayStartTime.getTime() > endTime.getTime()) {
                // list.add(getDateFormat(date, DATE_TIME_START));
                break;
            }
            i++;
            list.add(getDateFormat(date, DATE));
        }
        return list;
    }

    /**
     * 获取开始时间（小）到结束时间（大）内的月集合
     *
     * @param startTime
     * @param endTime
     *
     * @return
     */
    public static List<String> getCurrentMonthRange(Date startTime, Date endTime) {
        List<String> list = new ArrayList<>();
        list.add(getDateFormat(startTime, DATE_MONTH));
        int i = 1;
        while (true) {
            Date date = addMonth(startTime, (long) i);
            if (date.getTime() > endTime.getTime()) {
                // list.add(getDateFormat(date, DATE_TIME_START));
                break;
            }
            i++;
            list.add(getDateFormat(date, DATE_MONTH));
        }
        return list;
    }

    /**
     * 获取开始时间（小）到结束时间（大）内的年集合
     *
     * @param startTime
     * @param endTime
     *
     * @return
     */
    public static List<String> getCurrentYearRange(Date startTime, Date endTime) {
        List<String> list = new ArrayList<>();
        list.add(getDateFormat(startTime, DATE_YEAR));
        int i = 1;
        while (true) {
            Date date = addYear(startTime, (long) i);
            if (date.getTime() > endTime.getTime()) {
                // list.add(getDateFormat(date, DATE_TIME_START));
                break;
            }
            i++;
            list.add(getDateFormat(date, DATE_YEAR));
        }
        return list;
    }

    public static Date stringToDate(String strDate, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        return simpleDateFormat.parse(strDate, pos);
    }

    public static Date formatYMD(Date date) {
        String dateStr = format(date, DATE_TIME_START);
        return parse(dateStr, DATE_TIME);
    }

    /**
     * 获取几天前的时间
     *
     * @param beforDay
     * @return
     */
    public static Date getBeforeDay(Integer beforDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - beforDay);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取几天后的时间
     *
     * @param afterDay
     * @return
     */
    public static Date getAfterDay(Integer afterDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay);
        return getDayStartTime(calendar.getTime());
    }

    //获取某个日期的开始时间
    public static Date getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    //获取某个日期的结束时间
    public static Date getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static void main(String[] args) {
//        System.out.println(DateUtil.format(DateUtil.addDay(new Date(), -1L), DateUtil.DATE_TIME));
        String start = "2021-01-11 18:00:00";
        String end = "2021-01-12 10:00:00";
        System.out.println(getCurrentDayRange(parse(start,DATE_TIME),parse(end,DATE_TIME)));
    }

    //获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }
    //获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 获取本月是哪一天
     * @return
     */
    public static int getNowDay() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return  gc.get(Calendar.DATE);
    }

    public static int getDayOfWeek(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.getDayOfWeek().getValue();
    }

}
