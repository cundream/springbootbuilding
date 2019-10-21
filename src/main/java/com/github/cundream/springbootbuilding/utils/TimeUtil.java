package com.github.cundream.springbootbuilding.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间相关工具类
 *
 * @author LuPindong
 * @time 2017-04-30 08:52
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtil {
    public static final ZoneId ZONEID_SHANGHAI = ZoneId.of("Asia/Shanghai");

    public static final String DATE_DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static LocalDate nowLocalDate() {
        return LocalDate.now(ZONEID_SHANGHAI);
    }

    public static LocalTime nowLocalTime() {
        return LocalTime.now(ZONEID_SHANGHAI);
    }

    public static LocalDateTime nowLocalDateTime() {
        return LocalDateTime.now(ZONEID_SHANGHAI);
    }

    public static String format(LocalDateTime target) {
        return format(target, DATE_DEFAULT_PATTERN);
    }

    public static String format(LocalDateTime target, String pattern) {
        if (target == null) {
            return null;
        } else {
            return target.format(DateTimeFormatter.ofPattern(pattern));
        }
    }

    public static String format(LocalDate target, String pattern) {
        if (target == null) {
            return null;
        } else {
            return target.format(DateTimeFormatter.ofPattern(pattern));
        }
    }

    /**
     * 字符串转换为对应LocalDateTime
     *
     * @param source
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String source) {
        return stringToLocalDateTime(source, DATE_DEFAULT_PATTERN);
    }

    /**
     * 字符串转换为对应LocalDateTime
     *
     * @param source
     * @param pattern
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String source, String pattern) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        LocalDateTime localDateTime = null;
        try {
            localDateTime = dateToLocalDateTime(stringToDate(source, pattern));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return localDateTime;
    }

    /**
     * 字符串转换为对应LocalDate
     *
     * @param source
     * @param pattern
     * @return
     */
    public static LocalDate stringToLocalDate(String source, String pattern) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        LocalDate localDateTime = null;
        try {
            localDateTime = dateToLocalDate(stringToDate(source, pattern));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return localDateTime;
    }

    /**
     * 获取当前date
     *
     * @return
     */
    public static Date nowDateTime() {
        return localDateTimeToDate(nowLocalDateTime());
    }

    /**
     * date To LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(ZONEID_SHANGHAI).toLocalDateTime();
    }

    /**
     * date To LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(ZONEID_SHANGHAI).toLocalDate();
    }

    /**
     * localDateTime To Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZONEID_SHANGHAI);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * Date类型转为"yyyy-MM-dd HH:mm:ss"格式的String类型
     *
     * @param source
     * @return
     */
    public static String dateToString(Date source) {
        return dateToString(source, DATE_DEFAULT_PATTERN);
    }

    /**
     * Date类型转为指定格式的String类型
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String dateToString(Date source, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(source);
    }

    /**
     * 字符串转换为对应日期
     *
     * @param source
     * @return
     */
    public static Date stringToDate(String source) {
        return stringToDate(source, DATE_DEFAULT_PATTERN);
    }

    /**
     * 字符串转换为对应日期
     *
     * @param source
     * @param pattern
     * @return
     */
    public static Date stringToDate(String source, String pattern) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 获取某天的本周的第一天
     * @param localDate
     * @return
     */
    public static LocalDate firstDayOfWeek(LocalDate localDate){
        if(localDate!=null){
            TemporalField field=  WeekFields.of(DayOfWeek.MONDAY,1).dayOfWeek();
            localDate= localDate.with(field,1);
        }
        return localDate;
    }

    /**
     * 获取某天的本周的最后一天
     * @param localDate
     * @return
     */
    public static LocalDate lastDayOfWeek(LocalDate localDate){
        if(localDate!=null){
            TemporalField field=  WeekFields.of(DayOfWeek.MONDAY,1).dayOfWeek();
            localDate= localDate.with(field,7);
        }
        return localDate;
    }

    /**
     * 获取某天的本月第一天
     * @param localDate
     * @return
     */
    public static LocalDate firstDayOfMonth(LocalDate localDate){
        if(localDate!=null){
            TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstDayOfMonth();
            localDate = localDate.with(temporalAdjuster);
        }
        return localDate;
    }

    /**
     * 获取某天的下月第一天
     * @param localDate
     * @return
     */
    public static LocalDate firstDayOfNextMonth(LocalDate localDate){
        if(localDate!=null){
            TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstDayOfNextMonth();
            localDate = localDate.with(temporalAdjuster);
        }
        return localDate;
    }

    /**
     * 获取某天的本月最后一天
     * @param localDate
     * @return
     */
    public static LocalDate lastDayOfMonth(LocalDate localDate){
        if(localDate!=null){
            TemporalAdjuster temporalAdjuster = TemporalAdjusters.lastDayOfMonth();
            LocalDate with = localDate.with(temporalAdjuster);
        }
        return localDate;
    }

    public static String[] getMonthArray(String startStr, String endStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<String> monthList = new ArrayList<>();
        Calendar stCal = Calendar.getInstance();
        stCal.setTime(sdf.parse(startStr));
        Calendar enCal = Calendar.getInstance();
        enCal.setTime(sdf.parse(endStr));
        while(stCal.before(enCal)) {
            monthList.add(sdf.format(stCal.getTime()));
            stCal.add(Calendar.MONTH,1);
        }
        return monthList.toArray(new String[monthList.size()]);
    }
}