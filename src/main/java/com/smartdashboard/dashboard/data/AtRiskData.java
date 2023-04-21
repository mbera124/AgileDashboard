package com.smartdashboard.dashboard.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AtRiskData {
    private String developer;
    private String taskName;
    private LocalDate dueDate;
}
