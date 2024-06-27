package com.espere.pointing_system.service;

import com.espere.pointing_system.entity.Attendance;
import com.espere.pointing_system.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Setter
@NoArgsConstructor

public class AttendanceService {
    private List<Attendance> attendances;

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
    }

    public List<Attendance> getAttendancesByEmployee(Employee employee) {
        return attendances.stream()
                .filter(a -> a.getEmployee().equals(employee))
                .collect(Collectors.toList());
    }

    // Other methods to calculate overtime, enhanced hours, etc.
}

