package com.smartdashboard.modules.domain;

import com.smartdashboard.modules.data.TasksData;
import com.smartdashboard.modules.domain.Enumeration.Developers;
import com.smartdashboard.modules.domain.Enumeration.ModuleNames;
import com.smartdashboard.modules.domain.Enumeration.Region;
import com.smartdashboard.modules.domain.Enumeration.TaskProgress;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private Long taskCount;
    @Enumerated(EnumType.STRING)
    private Region region;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tasks_sprint_id"))
    private Sprints sprints;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tasks_module_id"))
    private Module module;
public TasksData toData(){
    TasksData tasksData=new TasksData();
    tasksData.setId(this.getId());
    tasksData.setModuleName(this.getModuleName().name());
    tasksData.setTaskCount(this.getTaskCount());
    tasksData.setTaskName(this.getTaskName());
    tasksData.setTaskProgress(this.getTaskProgress().name());
     return tasksData;
}
}
