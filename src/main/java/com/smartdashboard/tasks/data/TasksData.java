package com.smartdashboard.tasks.data;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TasksData {
    private Long id;
    private String moduleName;
    private String taskName;
    private String taskProgress;

    private Long sprintId;
    private String region;
    private LocalDate statrDate;
    private LocalDate endDate;
    private LocalDate actualEndDate;
    private List<DeveloperData> developerData;


}
