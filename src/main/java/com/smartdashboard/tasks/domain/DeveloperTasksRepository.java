package com.smartdashboard.tasks.domain;

import com.smartdashboard.tasks.domain.Enumeration.Developers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface DeveloperTasksRepository extends JpaRepository<DeveloperTasks, Long>, JpaSpecificationExecutor<DeveloperTasks> {
    DeveloperTasks findByTasks(Tasks tasks);
    List<DeveloperTasks> findAllByDevelopersAndTasks_EndDate(Developers developers, LocalDate date);
}
