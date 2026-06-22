package com.weng.system.lab;

import com.weng.system.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        /*String startTime = "2021-06-27 23:00:00";
        String endTime = "2021-06-28 10:00:00";
//        List<String> days = DateUtil.getCurrentDayRange(DateUtil.parse(startTime, DateUtil.DATE_TIME), DateUtil.parse(endTime, DateUtil.DATE_TIME));
        String substring = StringUtils.substring(startTime, 0, 10);
        System.out.println(substring);
        Date date = new Date();
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonth().getValue());

        String today = DateUtil.format(date, DateUtil.DATE);
        System.out.println(today);*/

        /*Date startDate = DateUtil.parse("2022-06-20 00:00:00", DateUtil.DATE_TIME);
        Date endDate = DateUtil.parse("2022-06-24 23:59:59", DateUtil.DATE_TIME);
        int dateIntervalDay = DateUtil.getDateIntervalDay(startDate, endDate);
        System.out.println(dateIntervalDay);*/
        /*Date now = new Date();
        String startTime = DateUtil.format(now, DateUtil.DATE_TIME_START);
        String endTime = DateUtil.format(now, DateUtil.DATE_TIME_END);
        // 当前周的第一天
        String firstDayOfCurrentWeek = DateUtil.getFirstDayOfCurrentWeek(DateUtil.DATE_TIME_START);
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(firstDayOfCurrentWeek);
        int dayOfWeek = DateUtil.getDayOfWeek(DateUtil.parse("2023-06-25 00:00:00", DateUtil.DATE_TIME));
        System.out.println(dayOfWeek);*/
        Date startDate = DateUtil.parse("1970/01/01 00:00:00", DateUtil.DATE_ONE);
        System.out.println(startDate);
    }
}
