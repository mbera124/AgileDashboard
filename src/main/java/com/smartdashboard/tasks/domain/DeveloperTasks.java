package com.smartdashboard.tasks.domain;

import com.smartdashboard.tasks.domain.Enumeration.Developers;
import com.smartdashboard.tasks.domain.Enumeration.Roles;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "developer_tasks")
public class DeveloperTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Developers developers;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_developer_tasks_task_id"))
    private Tasks tasks;
    @Enumerated(EnumType.STRING)
    private Roles role;

}
