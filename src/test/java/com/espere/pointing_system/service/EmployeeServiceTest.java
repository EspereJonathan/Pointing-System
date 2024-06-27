package com.espere.pointing_system.service;

import com.espere.pointing_system.entity.Attendance;
import com.espere.pointing_system.entity.Category;
import com.espere.pointing_system.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    public List<Attendance> attendances;

    @BeforeEach
    public void setUp() {
        openMocks(this);

        // Initialize the list of attendances
        attendances = new ArrayList<>();
    }

    @Test
    public void testCalculateWorkHoursAndSalaryWithPublicHolidays() {
        // Step 1: Add employees Rakoto and Rabe with Guardian category
        Category guardianCategory = new Category("Guardian", 7, 100000);
        Employee rakoto = new Employee("Rakoto", "Guard", "E001", LocalDate.of(1990, 1, 1),
                LocalDate.of(2020, 1, 1), LocalDate.of(2025, 12, 31), 100000, guardianCategory);
        Employee rabe = new Employee("Rabe", "Guard", "E002", LocalDate.of(1995, 1, 1),
                LocalDate.of(2020, 1, 1), LocalDate.of(2025, 12, 31), 100000, guardianCategory);

        employeeService.addEmployee(rakoto);
        employeeService.addEmployee(rabe);

        // Step 2: Define the schedule from May 26 to July 6 (6 weeks)
        LocalDate startDate = LocalDate.of(2024, 5, 26);
        LocalDate endDate = LocalDate.of(2024, 7, 6);
        LocalDate currentDate = startDate;
        int rakotoDays = 0;
        int rabeNights = 0;

        // Step 3: Calculate work hours and salaries
        double rakotoDailySalary = guardianCategory.getWeeklySalary() / 7.0;
        double rabeNightlySalary = guardianCategory.getWeeklySalary() / 7.0 * 1.3;

        List<LocalDate> publicHolidays = List.of(
                LocalDate.of(2024, 6, 17),
                LocalDate.of(2024, 6, 25),
                LocalDate.of(2024, 6, 26)
        );

        while (!currentDate.isAfter(endDate)) {
            if (!publicHolidays.contains(currentDate)) {
                rakotoDays++;
                rabeNights++;
            }
            currentDate = currentDate.plusDays(1);
        }

        // Correct the calculation of total salaries
        double rakotoTotalSalary = rakotoDays * rakotoDailySalary +
                publicHolidays.size() * rakotoDailySalary * 1.3;
        double rabeTotalSalary = rabeNights * rabeNightlySalary +
                publicHolidays.size() * rabeNightlySalary * 1.5; // 50% increase for night on public holiday

        // Step 4: Assertions
        assertEquals(39, rakotoDays, "Rakoto should work 39 days in 6 weeks excluding holidays");
        assertEquals(39, rabeNights, "Rabe should work 39 nights in 6 weeks excluding holidays");

        double expectedRakotoSalary = 39 * rakotoDailySalary + publicHolidays.size() * rakotoDailySalary * 1.3;
        double expectedRabeSalary = 39 * rabeNightlySalary + publicHolidays.size() * rabeNightlySalary * 1.5;

        assertEquals(expectedRakotoSalary, rakotoTotalSalary, 0.001, "Rakoto's total salary should be approximately " + expectedRakotoSalary + " Ar");
        assertEquals(expectedRabeSalary, rabeTotalSalary, 0.001, "Rabe's total salary should be approximately " + expectedRabeSalary + " Ar");
    }
}
