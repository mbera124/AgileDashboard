package com.smartdashboard.sprints.domain;

import com.smartdashboard.sprints.data.SprintData;
import com.smartdashboard.module.domain.enumeration.ModuleNames;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "sprints")
public class Sprints {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String sprintMonth;
    private String taskcount;
    private String sprintName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualCompletionDate;
    @Enumerated(EnumType.STRING)
    private ModuleNames module;
public SprintData toData(){
    SprintData sprintData =new SprintData();
    sprintData.setSprintMonth(this.getSprintMonth());
    sprintData.setId(this.getId());
    sprintData.setTaskcount(this.getTaskcount());
    sprintData.setSprintName(this.getSprintName());
    sprintData.setStartDate(this.getStartDate());
    sprintData.setEndDate(this.getEndDate());
    sprintData.setActualCompletionDate(this.getActualCompletionDate());
    sprintData.setModule(this.getModule().name());
    return sprintData;
}
}
