package com.espere.pointing_system;

import com.espere.pointing_system.entity.Category;
import com.espere.pointing_system.service.CalendarService;
import com.espere.pointing_system.service.EmployeeWorkCalculation;
import com.espere.pointing_system.service.SalaryCalculator;
import com.espere.pointing_system.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeWorkCalculationTest {

    private CalendarService calendarService;
    private Category guardianCategory;
    private Employee rakoto;
    private Employee rabe;

    @BeforeEach
    public void setUp() {
        calendarService = new CalendarService();
        guardianCategory = new Category("Guardian", 56, 110000);
        rakoto = new Employee("Rakoto", "Rakoto", "1", LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), null, 110000, 0, guardianCategory);
        rabe = new Employee("Rabe", "Rabe", "2", LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), null, 110000, 0, guardianCategory);
    }

    @Test
    public void testCalculateWorkHoursAndPay() {
        EmployeeWorkCalculation.calculateWorkHoursAndPay(calendarService, rakoto, rabe);

        // Expected results
        int expectedRakotoTotalHours = 22 * 8; // 22 work days in June 2024
        int expectedRakotoNightHours = 22 * 8; // Rakoto works every day including nights
        int expectedRabeNightHours = 22 * 8; // Rabe works every night
        int expectedRakotoSundayHours = 4 * 8; // 4 Sundays in June 2024
        int expectedRakotoHolidayHours = 3 * 8; // 3 holidays in June 2024

        double rakotoHourlyRate = guardianCategory.calculateHourlyRate();
        double rabeHourlyRate = guardianCategory.calculateHourlyRate();

        double expectedRakotoGrossSalary = expectedRakotoTotalHours * rakotoHourlyRate +
                SalaryCalculator.calculateNightPay(rakotoHourlyRate, expectedRakotoNightHours) +
                SalaryCalculator.calculateSundayPay(rakotoHourlyRate, expectedRakotoSundayHours) +
                SalaryCalculator.calculateHolidayPay(rakotoHourlyRate, expectedRakotoHolidayHours);
        double expectedRabeGrossSalary = expectedRakotoTotalHours * rabeHourlyRate +
                SalaryCalculator.calculateNightPay(rabeHourlyRate, expectedRabeNightHours) +
                SalaryCalculator.calculateSundayPay(rabeHourlyRate, expectedRakotoSundayHours) +
                SalaryCalculator.calculateHolidayPay(rabeHourlyRate, expectedRakotoHolidayHours);

        assertEquals(expectedRakotoTotalHours, rakoto.getGrossSalary(), "Rakoto's gross salary should be calculated correctly.");
        assertEquals(expectedRakotoTotalHours, rabe.getGrossSalary(), "Rabe's gross salary should be calculated correctly.");
        assertEquals(rakoto.calculateNetSalary(), rakoto.getNetSalary(), "Rakoto's net salary should be calculated correctly.");
        assertEquals(rabe.calculateNetSalary(), rabe.getNetSalary(), "Rabe's net salary should be calculated correctly.");
    }
}

