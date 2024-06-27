package com.espere.pointing_system.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    @Test
    public void testCalculateHourlyRate() {
        // Create a Category object
        Category category = new Category("Normal", 40, 100000);

        // Calculate hourly rate
        double expectedHourlyRate = 100000.0 / 40; // 2500.0

        // Verify the calculated hourly rate
        assertEquals(expectedHourlyRate, category.calculateHourlyRate(), 0.001,
                "Hourly rate calculation should be accurate");
    }

    @Test
    public void testSettersAndGetters() {
        Category category = new Category("Normal", 40, 100000);

        // Test setters and getters
        category.setName("Guard");
        assertEquals("Guard", category.getName(), "Category name should be updated");

        category.setNormalHoursPerWeek(56);
        assertEquals(56, category.getNormalHoursPerWeek(), "Normal hours per week should be updated");

        category.setWeeklySalary(110000);
        assertEquals(110000.0, category.getWeeklySalary(), 0.001, "Weekly salary should be updated");
    }
}
