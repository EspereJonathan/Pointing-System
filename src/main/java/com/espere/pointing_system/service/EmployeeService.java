package com.espere.pointing_system.service;

import com.espere.pointing_system.entity.Employee;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor

public class EmployeeService {
    private List<Employee> employees;
    // Default constructor
    public EmployeeService() {
        this.employees = new ArrayList<>();
    }
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

    public void setEmployees(ArrayList<Object> objects) {
    }
}

