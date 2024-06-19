package com.espere.pointing_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter

public class Employee {
    private String firstName;
    private String lastName;
    private String employeeId;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private LocalDate contractEndDate;
    private double grossSalary;
    private double netSalary;
    private Category category;


    public double calculateNetSalary() {
        return grossSalary * 0.8;
    }
}
