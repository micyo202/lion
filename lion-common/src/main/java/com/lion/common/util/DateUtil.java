/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * DateUtil
 * 日期工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/22
 */
public class DateUtil {

    private DateUtil() {}

    /**
     * 自定义格式化
     */
    public static final String YEAR_MONTH_FORMATTER = "yyyy-MM";
    public static final String DATE_FORMATTER = "yyyy-MM-dd";
    public static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMATTER = "HH:mm:ss";
    public static final String YEAR_MONTH_FORMATTER_SHORT = "yyyyMM";
    public static final String DATE_FORMATTER_SHORT = "yyyyMMdd";
    public static final String DATETIME_FORMATTER_SHORT = "yyyyMMddHHmmss";
    public static final String TIME_FORMATTER_SHORT = "HHmmss";

    /**
     * 获取当前时间戳
     *
     * @return timestamp
     */
    public static long getTimestamp() {
        /**
         * LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
         * LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
         * Timestamp.valueOf(LocalDateTime.now()).getTime();
         * Instant.now().toEpochMilli();
         */
        return Instant.now().toEpochMilli();
    }

    /**
     * 获取当前年份
     *
     * @return yyyy
     */
    public static int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    /**
     * 获取当前年月
     *
     * @return yyyy-MM
     */
    public static String getCurrentYearMonth() {
        return getCurrentDate(YEAR_MONTH_FORMATTER);
    }

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDate() {
        return getCurrentDate(DATE_FORMATTER);
    }

    /**
     * 获取下一天日期
     *
     * @return yyyy-MM-dd
     */
    public static String getNextDate() {
        return getNextDate(DATE_FORMATTER);
    }

    /**
     * 获取当前时间
     *
     * @return HHmmss
     */
    public static String getCurrentTime() {
        return getCurrentTime(TIME_FORMATTER);
    }

    /**
     * 获取当前日期时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTime() {
        return getCurrentDateTime(DATETIME_FORMATTER);
    }

    /**
     * 获取当前年月
     *
     * @return yyyyMM
     */
    public static String getCurrentYearMonthShort() {
        return getCurrentDate(YEAR_MONTH_FORMATTER_SHORT);
    }

    /**
     * 获取当前日期
     *
     * @return yyyyMMdd
     */
    public static String getCurrentDateShort() {
        return getCurrentDate(DATE_FORMATTER_SHORT);
    }

    /**
     * 获取下一天日期
     *
     * @return yyyy-MM-dd
     */
    public static String getNextDateShort() {
        return getNextDate(DATE_FORMATTER_SHORT);
    }

    /**
     * 获取当前时间
     *
     * @return HHmmss
     */
    public static String getCurrentTimeShort() {
        return getCurrentTime(TIME_FORMATTER_SHORT);
    }

    /**
     * 获取当前日期时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String getCurrentDateTimeShort() {
        return getCurrentDateTime(DATETIME_FORMATTER_SHORT);
    }

    /**
     * 获取当前日期
     *
     * @param pattern 格式化
     */
    public static String getCurrentDate(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取下一天日期
     *
     * @param pattern 格式化
     */
    public static String getNextDate(String pattern) {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间
     *
     * @param pattern 格式化
     */
    public static String getCurrentTime(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前日期时间
     *
     * @param pattern 格式化
     */
    public static String getCurrentDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 时间戳转日期
     *
     * @param timestamp 时间戳
     */
    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 日期转时间戳
     *
     * @param localDateTime 日期
     */
    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 时间戳转日期时间
     *
     * @param timestamp 时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatTimestamp(long timestamp) {
        return formatTimestamp(timestamp, DATETIME_FORMATTER);
    }

    /**
     * 时间戳转日期时间
     *
     * @param timestamp 时间戳
     * @return yyyyMMddHHmmss
     */
    public static String formatTimestampShort(long timestamp) {
        return formatTimestamp(timestamp, DATETIME_FORMATTER_SHORT);
    }

    /**
     * 时间戳转日期
     *
     * @param timestamp 时间戳
     * @param pattern   格式化
     */
    public static String formatTimestamp(long timestamp, String pattern) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return formatLocalDateTime(localDateTime, pattern);
    }

    /**
     * 日期时间转日期
     *
     * @param localDateTime 日期时间
     */
    public static LocalDate localDateTimeToLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    /**
     * 日期转日期时间
     *
     * @param localDate 日期
     */
    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    /**
     * 日期转日期时间
     *
     * @param localDate 日期
     * @param hour      小时
     * @param minute    分钟
     * @param second    秒
     */
    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate, int hour, int minute, int second) {
        return localDate.atTime(hour, minute, second);
    }

    /**
     * 日期转日期时间
     *
     * @param localDate 日期
     * @param localTime 时间
     */
    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return localDate.atTime(localTime);
    }

    /**
     * 日期格式化字符串
     *
     * @param localDate 日期
     * @return yyyy-MM-dd
     */
    public static String formatLocalDate(LocalDate localDate) {
        return formatLocalDate(localDate, DATE_FORMATTER);
    }

    /**
     * 日期格式化字符串
     *
     * @param localDate 日期
     * @return yyyyMMdd
     */
    public static String formatLocalDateShort(LocalDate localDate) {
        return formatLocalDate(localDate, DATE_FORMATTER_SHORT);
    }

    /**
     * 时间格式化字符串
     *
     * @param localTime 时间
     * @return HH:mm:ss
     */
    public static String formatLocalTime(LocalTime localTime) {
        return formatLocalTime(localTime, TIME_FORMATTER);
    }

    /**
     * 时间格式化字符串
     *
     * @param localTime 时间
     * @return HHmmss
     */
    public static String formatLocalTimeShort(LocalTime localTime) {
        return formatLocalTime(localTime, TIME_FORMATTER_SHORT);
    }

    /**
     * 日期时间格式化字符串
     *
     * @param localDateTime 日期时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime, DATETIME_FORMATTER);
    }

    /**
     * 日期时间格式化字符串
     *
     * @param localDateTime 日期时间
     * @return yyyyMMddHHmmss
     */
    public static String formatLocalDateTimeShort(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime, DATETIME_FORMATTER_SHORT);
    }

    /**
     * 日期格式化字符串
     *
     * @param localDate 日期
     * @param pattern   格式化
     */
    public static String formatLocalDate(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 时间格式化字符串
     *
     * @param localTime 时间
     * @param pattern   格式化
     */
    public static String formatLocalTime(LocalTime localTime, String pattern) {
        return localTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期时间格式化字符串
     *
     * @param localDateTime 日期时间
     * @param pattern       格式化
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期字符串转日期
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate parseLocalDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 时间字符串转时间
     *
     * @param time    时间字符串
     * @param pattern 格式化
     */
    public static LocalTime parseLocalTime(String time, String pattern) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期时间字符串转日期时间
     *
     * @param dateTime 日期时间字符串
     * @param pattern  格式化
     */
    public static LocalDateTime parseLocalDateTime(String dateTime, String pattern) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取本周第一天
     */
    public static LocalDate getCurrentWeekFirstDate() {
        return LocalDate.now().minusWeeks(0).with(DayOfWeek.MONDAY);
    }

    /**
     * 获取本周最后一天
     */
    public static LocalDate getCurrentWeekLastDate() {
        return LocalDate.now().minusWeeks(0).with(DayOfWeek.SUNDAY);
    }

    /**
     * 获取本月第一天
     */
    public static LocalDate getCurrentMonthFirstDate() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取本月最后一天
     */
    public static LocalDate getCurrentMonthLastDate() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取指定周第一天
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate getWeekFirstDate(String date, String pattern) {
        return parseLocalDate(date, pattern).minusWeeks(0).with(DayOfWeek.MONDAY);
    }

    /**
     * 获取指定周第一天
     *
     * @param localDate 日期
     */
    public static LocalDate getWeekFirstDate(LocalDate localDate) {
        return localDate.minusWeeks(0).with(DayOfWeek.MONDAY);
    }

    /**
     * 获取指定周最后一天
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate getWeekLastDate(String date, String pattern) {
        return parseLocalDate(date, pattern).minusWeeks(0).with(DayOfWeek.SUNDAY);
    }

    /**
     * 获取指定周最后一天
     *
     * @param localDate 日期
     */
    public static LocalDate getWeekLastDate(LocalDate localDate) {
        return localDate.minusWeeks(0).with(DayOfWeek.SUNDAY);
    }


    /**
     * 获取指定月份月第一天
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate getMonthFirstDate(String date, String pattern) {
        return parseLocalDate(date, pattern).with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取指定月份月第一天
     *
     * @param localDate 日期
     */
    public static LocalDate getMonthFirstDate(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取当前星期
     *
     * @return 1:星期一、2:星期二、3:星期三、4:星期四、5:星期五、6:星期六、7:星期日
     */
    public static int getCurrentWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }


    /**
     * 获取当前星期
     *
     * @param localDate 日期
     */
    public static int getWeek(LocalDate localDate) {
        return localDate.getDayOfWeek().getValue();
    }

    /**
     * 获取指定日期的星期
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static int getWeek(String date, String pattern) {
        return parseLocalDate(date, pattern).getDayOfWeek().getValue();
    }

    /**
     * 日期相隔天数
     *
     * @param startLocalDate 起日期
     * @param endLocalDate   止日期
     */
    public static long intervalDays(LocalDate startLocalDate, LocalDate endLocalDate) {
        return endLocalDate.toEpochDay() - startLocalDate.toEpochDay();
    }

    /**
     * 日期时间相隔天数
     *
     * @param startLocalDateTime 起日期时间
     * @param endLocalDateTime   止日期时间
     */
    public static long intervalDays(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        return Duration.between(startLocalDateTime, endLocalDateTime).toDays();
    }

    /**
     * 日期时间相隔小时
     *
     * @param startLocalDateTime 起日期时间
     * @param endLocalDateTime   止日期时间
     */
    public static long intervalHours(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        return Duration.between(startLocalDateTime, endLocalDateTime).toHours();
    }

    /**
     * 日期时间相隔分钟
     *
     * @param startLocalDateTime 起日期时间
     * @param endLocalDateTime   止日期时间
     */
    public static long intervalMinutes(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        return Duration.between(startLocalDateTime, endLocalDateTime).toMinutes();
    }

    /**
     * 日期时间相隔毫秒
     *
     * @param startLocalDateTime 起日期时间
     * @param endLocalDateTime   止日期时间
     */
    public static long intervalMillis(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        return Duration.between(startLocalDateTime, endLocalDateTime).toMillis();
    }

    /**
     * 当前是否闰年
     */
    public static boolean isCurrentLeapYear() {
        return LocalDate.now().isLeapYear();
    }

    /**
     * 是否闰年
     */
    public static boolean isLeapYear(LocalDate localDate) {
        return localDate.isLeapYear();
    }

    /**
     * 是否当天
     *
     * @param localDate 日期
     */
    public static boolean isToday(LocalDate localDate) {
        return LocalDate.now().equals(localDate);
    }

    /**
     * 获取此日期时间与默认时区<Asia/Shanghai>组合的时间毫秒数
     *
     * @param localDateTime 日期时间
     */
    public static Long toEpochMilli(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取此日期时间与指定时区组合的时间毫秒数
     *
     * @param localDateTime 日期时间
     */
    public static Long toSelectEpochMilli(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * 给日期增加指定单位数量
     *
     * 支持单位：
     * Years
     * Months
     * Weeks
     * Days
     *
     * @param localDate 日期
     * @param unit      单位
     * @param amount    增加值
     */
    public static LocalDate datePlus(LocalDate localDate, ChronoUnit unit, int amount) {
        return localDate.plus(amount, unit);
    }

    /**
     * 给日期时间增加指定单位数量
     *
     * 支持单位：
     * Years
     * Months
     * Weeks
     * Days
     * Hours
     * Minutes
     * Seconds
     * Nanos
     *
     * @param localDateTime 日期
     * @param unit          单位
     * @param amount        增加值
     */
    public static LocalDateTime dateTimePlus(LocalDateTime localDateTime, ChronoUnit unit, int amount) {
        return localDateTime.plus(amount, unit);
    }
}