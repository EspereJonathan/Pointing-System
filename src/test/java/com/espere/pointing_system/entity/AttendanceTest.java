package com.espere.pointing_system.entity;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor

public class AttendanceTest {

    private Employee employee;
    private LocalDate date;
    private Attendance attendance;

    @BeforeEach
    public void setUp() {
        // Initialize the Employee object
        employee = new Employee("John", "Doe", "12345", LocalDate.of(1985, 5, 20),
                LocalDate.of(2010, 6, 15), LocalDate.of(2025, 6, 15), 100000,
                new Category("Normal", 40, 100000));
        date = LocalDate.of(2024, 6, 10);
        attendance = new Attendance(employee, date, 8);
    }

    @Test
    public void testAttendanceCreation() {
        // Verify the fields of the Attendance object
        assertEquals(employee, attendance.getEmployee(), "Employee should match the one set in the constructor");
        assertEquals(date, attendance.getDate(), "Date should match the one set in the constructor");
        assertEquals(8, attendance.getHoursWorked(), "Hours worked should match the one set in the constructor");
    }

    @Test
    public void testSetHoursWorked() {
        attendance.setHoursWorked(10);
        assertEquals(10, attendance.getHoursWorked(), "Hours worked should be updated to 10");
    }

    @Test
    public void testSetDate() {
        LocalDate newDate = LocalDate.of(2024, 6, 11);
        attendance.setDate(newDate);
        assertEquals(newDate, attendance.getDate(), "Date should be updated to the new date");
    }

    @Test
    public void testSetEmployee() {
        Employee newEmployee = new Employee("Jane", "Doe", "54321", LocalDate.of(1990, 7, 15),
                LocalDate.of(2015, 8, 20), LocalDate.of(2030, 8, 20), 120000,
                new Category("Guard", 56, 110000));
        attendance.setEmployee(newEmployee);
        assertEquals(newEmployee, attendance.getEmployee(), "Employee should be updated to the new employee");
    }
}
