package com.espere.pointing_system.service;

import com.espere.pointing_system.entity.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private List<Employee> employees;

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Optional<Employee> findEmployee(String employeeId) {
        return employees.stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .findFirst();
    }

    public double calculateNetSalary(double grossSalary) {
        return grossSalary * 0.8;
    }
}

