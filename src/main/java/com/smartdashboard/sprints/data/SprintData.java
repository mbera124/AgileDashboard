package com.smartdashboard.sprints.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SprintData {
    private Long id;
    private String sprintMonth;
    private String taskcount;
    private String sprintName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualCompletionDate;
    private String module;
}
