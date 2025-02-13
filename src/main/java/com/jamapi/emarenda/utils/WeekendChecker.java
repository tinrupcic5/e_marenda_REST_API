package com.jamapi.emarenda.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;

public class WeekendChecker {

    public static boolean isTomorrowWeekend() {
        LocalDate tomorrow = LocalDate.now(ZoneId.of("Europe/Zagreb")).plusDays(1);
        DayOfWeek dayOfWeek = tomorrow.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
