package com.espere.pointing_system.service;

import com.espere.pointing_system.entity.Calendar;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarService {
    private final Calendar calendar;

    public CalendarService() {
        calendar = new Calendar();
        calendar.setPublicHolidays(new ArrayList<>());
        calendar.getPublicHolidays().add(LocalDate.of(2024, 6, 17));
        calendar.getPublicHolidays().add(LocalDate.of(2024, 6, 25));
        calendar.getPublicHolidays().add(LocalDate.of(2024, 6, 26));
    }

    public boolean isPublicHoliday(LocalDate date) {
        return calendar.getPublicHolidays().contains(date);
    }
}

