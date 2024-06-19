package com.espere.pointing_system.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalendarService {
    private final Set<LocalDate> publicHolidays;

    public CalendarService() {
        publicHolidays = new HashSet<>();
        publicHolidays.add(LocalDate.of(2024, 6, 17));
        publicHolidays.add(LocalDate.of(2024, 6, 25));
        publicHolidays.add(LocalDate.of(2024, 6, 26));
    }

    public boolean isPublicHoliday(LocalDate date) {
        return publicHolidays.contains(date);
    }

    public List<LocalDate> getWorkDaysForEmployee(LocalDate startDate, LocalDate endDate, boolean isGuardian) {
        List<LocalDate> workDays = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            if (isGuardian || (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY)) {
                workDays.add(currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }

        return workDays;
    }
}

