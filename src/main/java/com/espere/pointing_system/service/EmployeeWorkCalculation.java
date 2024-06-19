package com.espere.pointing_system.service;

import com.espere.pointing_system.entity.Employee;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class EmployeeWorkCalculation {

    public static void calculateWorkHoursAndPay(CalendarService calendar, Employee rakoto, Employee rabe) {
        List<LocalDate> workDays = calendar.getWorkDaysForEmployee(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 30), true);

        int rakotoTotalHours = 0;
        int rabeTotalHours = 0;
        int rakotoNightHours = 0;
        int rabeNightHours = 0;
        int rakotoSundayHours = 0;
        int rabeSundayHours = 0;
        int rakotoHolidayHours = 0;
        int rabeHolidayHours = 0;

        for (LocalDate date : workDays) {
            if (calendar.isPublicHoliday(date)) {
                rakotoHolidayHours += 8;
                rabeHolidayHours += 8;
            } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                rakotoSundayHours += 8;
                rabeSundayHours += 8;
            } else {
                rakotoTotalHours += 8;
                rabeTotalHours += 8;
                rakotoNightHours += 8; // Rabe works nights by default
            }
        }

        double rakotoHourlyRate = rakoto.getCategory().calculateHourlyRate();
        double rabeHourlyRate = rabe.getCategory().calculateHourlyRate();

        double rakotoGrossSalary = rakotoTotalHours * rakotoHourlyRate +
                SalaryCalculator.calculateNightPay(rakotoHourlyRate, rakotoNightHours) +
                SalaryCalculator.calculateSundayPay(rakotoHourlyRate, rakotoSundayHours) +
                SalaryCalculator.calculateHolidayPay(rakotoHourlyRate, rakotoHolidayHours);
        double rabeGrossSalary = rabeTotalHours * rabeHourlyRate +
                SalaryCalculator.calculateNightPay(rabeHourlyRate, rabeNightHours) +
                SalaryCalculator.calculateSundayPay(rabeHourlyRate, rabeSundayHours) +
                SalaryCalculator.calculateHolidayPay(rabeHourlyRate, rabeHolidayHours);

        rakoto.setGrossSalary(rakotoGrossSalary);
        rakoto.setNetSalary(rakoto.calculateNetSalary());
        rabe.setGrossSalary(rabeGrossSalary);
        rabe.setNetSalary(rabe.calculateNetSalary());
    }
}

