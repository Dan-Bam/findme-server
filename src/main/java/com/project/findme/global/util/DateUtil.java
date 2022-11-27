package com.project.findme.global.util;

import java.time.LocalDateTime;

public class DateUtil {

    public static String toTimeAge(LocalDateTime localDateTime) {

        LocalDateTime now = LocalDateTime.now();

        if (localDateTime.getYear() != now.getYear()) {
            return yearDifference(now, localDateTime) + "년 전";
        } else if (localDateTime.getMonth() != now.getMonth()) {
            return monthDifference(now, localDateTime) + "달 전";
        } else if (localDateTime.getDayOfMonth() != now.getDayOfMonth()) {
            return dayDifference(now, localDateTime) + "일 전";
        } else if (localDateTime.getHour() != now.getHour()) {
            return hourDifference(now, localDateTime) + "시간 전";
        } else if (localDateTime.getMinute() != now.getMinute()) {
            return minuteDifference(now, localDateTime) + "분 전";
        } else
            return "방금 전";
    }

    private static int yearDifference(LocalDateTime now, LocalDateTime localDateTime) {
        return now.minusYears(localDateTime.getYear()).getYear();
    }

    private static int monthDifference(LocalDateTime now, LocalDateTime localDateTime) {
        return now.minusMonths(localDateTime.getMonth().getValue()).getMonth().getValue();
    }

    private static int dayDifference(LocalDateTime now, LocalDateTime localDateTime) {
        return now.minusDays(localDateTime.getDayOfMonth()).getDayOfMonth();
    }

    private static int hourDifference(LocalDateTime now, LocalDateTime localDateTime) {
        return now.minusHours(localDateTime.getHour()).getHour();
    }

    private static int minuteDifference(LocalDateTime now, LocalDateTime localDateTime) {
        return now.minusMinutes(localDateTime.getMinute()).getMinute();
    }

}
