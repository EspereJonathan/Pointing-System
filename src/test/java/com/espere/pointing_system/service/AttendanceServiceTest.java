package com.espere.pointing_system.service;

import com.espere.pointing_system.entity.Attendance;
import com.espere.pointing_system.entity.Employee;
import com.espere.pointing_system.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendanceServiceTest {

    private AttendanceService attendanceService;
    private Employee employee;

    @BeforeEach
    public void setUp() {
        // Initialize AttendanceService and Employee objects
        attendanceService = new AttendanceService();
        employee = new Employee("John", "Doe", "E12345", LocalDate.of(1985, 5, 20),
                LocalDate.of(2010, 6, 15), LocalDate.of(2025, 6, 15), 100000,
                80000, new Category("Normal", 40, 100000));
    }

    @Test
    public void testAddAttendance() {
        // Create an Attendance object
        Attendance attendance = new Attendance(employee, LocalDate.of(2024, 6, 10), 8);

        // Add the attendance record
        attendanceService.addAttendance(attendance);

        // Retrieve the list of attendances
        List<Attendance> attendances = attendanceService.getAttendancesByEmployee(employee);

        // Verify that the added attendance record exists in the list
        assertEquals(1, attendances.size(), "There should be 1 attendance record");
        assertEquals(attendance, attendances.getFirst(), "Added attendance record should match the retrieved one");
    }

    @Test
    public void testGetAttendancesByEmployee() {
        // Add multiple attendance records
        Attendance attendance1 = new Attendance(employee, LocalDate.of(2024, 6, 10), 8);
        Attendance attendance2 = new Attendance(employee, LocalDate.of(2024, 6, 11), 7);
        Attendance attendance3 = new Attendance(employee, LocalDate.of(2024, 6, 12), 9);

        for (Attendance attendance : Arrays.asList(attendance1, attendance2, attendance3)) {
            attendanceService.addAttendance(attendance);
        }

        // Retrieve attendances for the employee
        List<Attendance> attendances = attendanceService.getAttendancesByEmployee(employee);

        // Verify the size and contents of the list
        assertEquals(3, attendances.size(), "There should be 3 attendance records for the employee");
        assertEquals(attendance1, attendances.get(0), "First attendance record should match");
        assertEquals(attendance2, attendances.get(1), "Second attendance record should match");
        assertEquals(attendance3, attendances.get(2), "Third attendance record should match");
    }
}
