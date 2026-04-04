package com.weng.system.common.utils;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateUtil2 {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 例如:2018-12-28
     */
    public static final String DATE = "yyyy-MM-dd";
    /**
     * 例如:2018-12-28 10:00:00
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_MINUTE = "yyyy-MM-dd HH:mm";

    public static final String DATE_TIME_START = "yyyy-MM-dd 00:00:00";

    public static final String DATE_TIME_END = "yyyy-MM-dd 23:59:59";
    /**
     * 例如:2018/12/28 10:00:00
     */
    public static final String DATE_TIME_NEW = "yyyy/MM/dd HH:mm:ss";
    /**
     *
     */
    public static final String START_DATE_HOURS = "yyyy-MM-dd HH:00:00";

    public static final String END_DATE_HOURS = "yyyy-MM-dd HH:59:59";

    public static final String DATE_ONE = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_TWO = "yyyyMMddHHmmss";

    public static final String NUM_ONE = "00:00:00";

    public static final String NUM_TWO = "0000";

    public static final String NUM_THREE = " 23:59:59";
    /**
     * 例如:10
     */
    public static final String HOURS = "HH";
    /**
     * 例如:10日10时
     */
    public static final String TIME_CHARTS = "dd日HH时";

    /**
     * 例如:10月10日
     */
    public static final String TIME_CHARTS_DAY = "MM月dd日";

    /**
     * 例如:2018-12-28 10:00:00
     */
    public static final String DATE_TIME_ONE = "yyyy年MM月dd日 HH:mm:ss";
    /**
     * 例如:10:00:00
     */
    public static final String TIME = "HHmmss";
    /**
     * 例如:10:00
     */
    public static final String TIME_WITHOUT_SECOND = "HH:mm";

    /**
     * 例如:2018-12-28 10:00
     */
    public static final String DATE_TIME_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";

    // 时间元素
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String WEEK = "week";
    private static final String DAY = "day";
    private static final String HOUR = "hour";
    private static final String MINUTE = "minute";
    private static final String SECOND = "second";
    // 根据指定格式显示日期和时间
    /**
     * yyyy-MM-dd
     */
    private static final DateTimeFormatter yyyyMMdd_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateUtil2() {

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
     * 获取当前LocalDateTime时间
     */
    public static LocalDateTime getNow(){
        return LocalDateTime.now(ZoneId.systemDefault());
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
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 解析字符串日期为Date
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return Date
     */
    public static Date parse(String dateStr, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 解析字符串日期为LocalDateTime
     *
     * @param dateString 日期字符串
     * @param pattern 格式
     * @return LocalDateTime
     */
    public static LocalDateTime stringToLocalDateTime(String dateString, String pattern) {
        return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串日期为Date
     * @param dateStr yyyy-MM-dd 格式
     * @param pattern
     * @return
     */
    public static Date pars(String dateStr, String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDate parse = LocalDate.parse(dateStr, dtf);
        return localDate2Date(parse);
    }


    /**
     * 为Date增加分钟,减传负数
     *
     * @param date        日期
     * @param plusMinutes 要增加的分钟数
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
     * @param date
     *            date
     * @param days
     *            要增加的天数
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
     * @param date date
     * @param hour 要增加的小时数
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
     * @param date  date
     * @param month 要增加的月数
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
     * @return Date
     */
    public static Date minusMonths(long monthsToSubtract) {
        LocalDate localDate = LocalDate.now().minusMonths(monthsToSubtract);
        return localDate2Date(localDate);
    }

    /**
     * LocalDate类型转为Date
     *
     * @param localDate LocalDate object
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
     * @return Date object
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 查询当前年的第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
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
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
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
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
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

    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = sdf.parse(date);
        return DateUtil2.getDateFormat(d, format);
    }

    public static String dateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(date);
    }

    /**
     * @param time 时间
     * @param num  加的数，-num就是减去
     * @return 减去相应的数量的天的日期
     * @throws ParseException Date
     */
    public static Date dayAddNum(Date time, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        Date newTime = calendar.getTime();
        return newTime;
    }

    /**
     * 获取时间间隔(小时)
     *
     * @param dateNow
     * @param dateOld
     * @return
     */
    public static int getDateIntervalHour(Date dateNow, Date dateOld) {
        Instant now = dateNow.toInstant();
        Instant old = dateOld.toInstant();
        return Integer.parseInt(Duration.between(now, old).toHours() + "");
    }

    public static int getDateIntervalMinutes(Date dateNow, Date dateOld) {
        Instant now = dateNow.toInstant();
        Instant old = dateOld.toInstant();
        return Integer.parseInt(Duration.between(now, old).toMinutes() + "");
    }

    public static int getDateIntervalMillis(Date dateNow, Date dateOld) {
        Instant now = dateNow.toInstant();
        Instant old = dateOld.toInstant();
        return Integer.parseInt(Duration.between(now, old).toMillis() + "");
    }

    /**
     * 获取时间间隔(天)
     *
     * @param dateNow
     * @param dateOld
     * @return
     */
    public static int getDateIntervalDay(Date dateNow, Date dateOld) {
        Instant now = dateNow.toInstant();
        Instant old = dateOld.toInstant();
        return Integer.parseInt(Duration.between(now, old).toDays() + "");
    }

    /**
     * 检测：输入年份是否是闰年？
     *
     * @param date 日期格式：yyyy-MM-dd
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
                String tmp = getDateFormat(addHour(getStartTime(), (long) i), DATE_TIME);
                list.add(tmp);
                i++;
            }
        }
        return list;
    }

    /**
     * 获取当前开始->结束时间，传入天数的前/后N天小时集合，含当天，负数为前N天
     *
     * @param
     * @return
     */
    public static List<String> getCurrentTimeHourDateRange(Integer days) {
        List<String> list = new ArrayList<>();
        if (days > 0) {
            for (int i = 0; i < days * 24; i++) {
                String tmp = getDateFormat(addHour(new Date(), (long) i),
                        DATE_TIME);
                // String tmp = getDateFormat(new Date(), DATE_TIME);
                list.add(tmp);
            }
        } else {
            // System.out.println( days * 24);
            Long i = (long) (days * 24);
            while (i < 24) {
                // System.out.println( days * 24);
                // System.out.println( i * 24);
                String tmp = getDateFormat(addHour(new Date(), (long) i), DATE_TIME);
                list.add(tmp);
                i++;
            }
        }
        return list;
    }

    /**
     * 获取当前日期的前N天的开始时间集合  正序
     *
     * @param N
     * @return
     */
    public static List<Date> getNDaysBefore(int N) {
        List<Date> result = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        for (int i = 0; i < N; i++) {
            LocalDateTime localDateTime = now.plusDays(-(N - i - 1));
            result.add(localDateTime2Date(localDateTime));
        }
        return result;
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
     * 得到当前时间n小时后的时间
     *
     * @param hour
     * @return
     */
    public static Date getDayAfterHour(Date date, Integer hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + hour);
        return calendar.getTime();
    }

    /**
     * 获取时间段里每一天的时间
     * @param startTime  2020-01-01 00:00:00
     * @param endTime    2020-01-04 00:00:00
     * @return
     */
    public static List<Date> findDates(Date startTime,Date endTime){
        List<Date> result = new ArrayList();
        result.add(startTime);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 时间
        calBegin.setTime(startTime);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 时间
        calEnd.setTime(endTime);
        //获取相差天数
        long disDay = getDisDay(startTime, endTime);
        for (long i = 0; i < disDay; i++) {
            // 给定的字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            result.add(calBegin.getTime());
        }
        return result;
    }

    /**
     * 日期差天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDisDay(Date startDate, Date endDate){
        long[] dis = getDisTime(startDate, endDate);
        long day = dis[0];
        if (dis[1] > 0 || dis[2] > 0 || dis[3] > 0) {
            day += 1;
        }
        return day;
    }

    /**
     * 日期差天数、小时、分钟、秒数组
     * @param startDate
     * @param endDate
     * @return
     */
    public static long[] getDisTime(Date startDate, Date endDate){
        long timesDis = Math.abs(startDate.getTime() - endDate.getTime());
        long day = timesDis / (1000 * 60 * 60 * 24);
        long hour = timesDis / (1000 * 60 * 60) - day * 24;
        long min = timesDis / (1000 * 60) - day * 24 * 60 - hour * 60;
        long sec = timesDis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        return new long[]{day, hour, min, sec};
    }

    /**
     * Date转LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * Date转LocalDate
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date){
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    /**
     * 获取时间范围里 每一天的时间字符串（2020-02-13）
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getPieDateRange(Date startDate, Date endDate) {
        List<String> result = Lists.newArrayList();
        LocalDate end = dateToLocalDate(endDate);
        LocalDate start = dateToLocalDate(startDate);
        LocalDate tmp = start;
        while (start.isBefore(end) || start.isEqual(end)) {
            result.add(start.toString());
            start = start.plusDays(1);
        }
        return result;
    }
}
