package com.espere.pointing_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter

public class Attendance {
    private Employee employee;
    private LocalDate date;
    private int hoursWorked;

}
