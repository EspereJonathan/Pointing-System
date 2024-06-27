package com.espere.pointing_system.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    public void testCalculateNetSalary() {
        // Create a Category object
        Category category = new Category("Normal", 40, 100000);

        // Create an Employee object
        Employee employee = new Employee("John", "Doe", "E12345", LocalDate.of(1985, 5, 20),
                LocalDate.of(2010, 6, 15), LocalDate.of(2025, 6, 15), 100000, category);

        // Calculate net salary
        double expectedNetSalary = 100000 * 0.8; // 80000.0

        // Verify the calculated net salary
        assertEquals(expectedNetSalary, employee.calculateNetSalary(), 0.001,
                "Net salary calculation should be accurate");
    }

    @Test
    public void testSettersAndGetters() {
        Category category = new Category("Normal", 40, 100000);

        // Create an Employee object
        Employee employee = new Employee("John", "Doe", "E12345", LocalDate.of(1985, 5, 20),
                LocalDate.of(2010, 6, 15), LocalDate.of(2025, 6, 15), 100000, category);

        // Test setters and getters
        employee.setFirstName("Jane");
        assertEquals("Jane", employee.getFirstName(), "First name should be updated");

        employee.setGrossSalary(120000);
        assertEquals(120000.0, employee.getGrossSalary(), 0.001, "Gross salary should be updated");

        // Ensure that category getter/setter works correctly
        employee.setCategory(new Category("Guard", 56, 110000));
        assertEquals("Guard", employee.getCategory().getName(), "Category should be updated");

        // Test calculateNetSalary after updating gross salary
        double expectedNetSalaryAfterUpdate = 120000 * 0.8;
        assertEquals(expectedNetSalaryAfterUpdate, employee.calculateNetSalary(), 0.001,
                "Net salary calculation should be accurate after gross salary update");
    }
}
