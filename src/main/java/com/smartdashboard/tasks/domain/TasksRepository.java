package com.smartdashboard.tasks.domain;

import com.smartdashboard.tasks.domain.Enumeration.TaskProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
List<Tasks> findAllByEndDateAndTaskProgress(LocalDate date, TaskProgress taskProgress);
}
