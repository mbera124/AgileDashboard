package com.smartdashboard.tasks.domain;

import com.smartdashboard.sprints.domain.Sprints;
import com.smartdashboard.tasks.data.TasksData;
import com.smartdashboard.module.domain.enumeration.ModuleNames;
import com.smartdashboard.tasks.domain.Enumeration.Region;
import com.smartdashboard.tasks.domain.Enumeration.TaskProgress;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ModuleNames moduleName;
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualEndDate;
    @Enumerated(EnumType.STRING)
    private TaskProgress taskProgress;
    @Enumerated(EnumType.STRING)
    private Region region;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tasks_sprint_id"))
    private Sprints sprints;

    public TasksData toData() {
        TasksData tasksData = new TasksData();
        tasksData.setId(this.getId());
        tasksData.setModuleName(this.getModuleName().name());
        tasksData.setTaskName(this.getTaskName());
        tasksData.setTaskProgress(this.getTaskProgress().name());
        tasksData.setSprintId(this.getSprints().getId());
        tasksData.setRegion(this.getRegion().name());
        tasksData.setEndDate(this.getEndDate());
        tasksData.setStatrDate(this.getStartDate());
        tasksData.setActualEndDate(this.getActualEndDate());
        return tasksData;
    }
}
