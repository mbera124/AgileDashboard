package com.smartdashboard.modules.domain;

import com.smartdashboard.modules.data.TasksData;
import com.smartdashboard.modules.domain.Enumeration.ModuleNames;
import com.smartdashboard.modules.domain.Enumeration.TaskProgress;
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
    private LocalDate endDtate;
    private LocalDate actualCompletionDate;
    @Enumerated(EnumType.STRING)
    private ModuleNames module;

}
