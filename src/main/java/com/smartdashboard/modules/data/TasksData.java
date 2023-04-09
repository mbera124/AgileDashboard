package com.smartdashboard.modules.data;

import lombok.Data;
@Data
public class TasksData {
    private Long id;
    private String moduleName;
    private String taskName;
    private Long devDuration;
    private String taskProgress;
    private Long taskCount;
}
