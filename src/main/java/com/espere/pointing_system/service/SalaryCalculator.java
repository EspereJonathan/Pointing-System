package com.espere.pointing_system.service;

public class SalaryCalculator {
    public static double calculateOvertimePay(double hourlyRate, int overtimeHours) {
        int hs30Hours = Math.min(overtimeHours, 8);
        int hs50Hours = Math.max(overtimeHours - 8, 0);

        return hs30Hours * hourlyRate * 1.3 + hs50Hours * hourlyRate * 1.5;
    }

    public static double calculateNightPay(double hourlyRate, int nightHours) {
        return nightHours * hourlyRate * 1.3;
    }

    public static double calculateSundayPay(double hourlyRate, int sundayHours) {
        return sundayHours * hourlyRate * 1.4;
    }

    public static double calculateHolidayPay(double hourlyRate, int holidayHours) {
        return holidayHours * hourlyRate * 1.5;
    }
}
