package com.smartdashboard.dashboard.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskPerDeveloper {
    private String developer;
    private int taskCount;
}
