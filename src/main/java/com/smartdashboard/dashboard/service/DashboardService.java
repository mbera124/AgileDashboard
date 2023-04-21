package com.smartdashboard.dashboard.service;

import com.smartdashboard.dashboard.data.AtRiskData;
import com.smartdashboard.dashboard.data.TaskPerDeveloper;
import com.smartdashboard.dashboard.domain.specification.DashboardSpecification;
import com.smartdashboard.tasks.domain.DeveloperTasks;
import com.smartdashboard.tasks.domain.DeveloperTasksRepository;
import com.smartdashboard.tasks.domain.Enumeration.Developers;
import com.smartdashboard.tasks.domain.Enumeration.TaskProgress;
import com.smartdashboard.tasks.domain.Tasks;
import com.smartdashboard.tasks.domain.TasksRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class DashboardService {
    private final TasksRepository tasksRepository;
    private final DeveloperTasksRepository developerTasksRepository;

    public List<AtRiskData> findAtRiskTasks(LocalDate date) {
        List<Tasks> tasks = tasksRepository.findAllByEndDateAndTaskProgress(date, TaskProgress.Development);
        List<AtRiskData> atRiskDataList = new ArrayList<>();
        if (tasks != null && !tasks.isEmpty()) {
            tasks.forEach(x -> {
                AtRiskData atRiskData = new AtRiskData();
                DeveloperTasks developerTasks = developerTasksRepository.findByTasks(x);
                atRiskData.setDeveloper(developerTasks.getDevelopers().name());
                atRiskData.setTaskName(x.getTaskName());
                atRiskData.setDueDate(x.getEndDate());
                atRiskDataList.add(atRiskData);
            });
        }
        return atRiskDataList;
    }

    public List<TaskPerDeveloper> findTaskPerDevelopers(Developers developers, LocalDate localDate) {
        Specification<DeveloperTasks> developerTasksSpecification = DashboardSpecification.createSpecification(developers, localDate);
        Map<Developers, List<DeveloperTasks>> developerTasksList = developerTasksRepository.findAll(developerTasksSpecification).stream().collect(Collectors.groupingBy(x->x.getDevelopers()));
        List<TaskPerDeveloper> taskPerDeveloperList = new ArrayList<>();
        for (Map.Entry<Developers, List<DeveloperTasks>>  map : developerTasksList.entrySet()){
            Developers developers1=map.getKey();
            List<DeveloperTasks> developerTasksList1=map.getValue();
            TaskPerDeveloper taskPerDeveloper = new TaskPerDeveloper();
            taskPerDeveloper.setDeveloper(developers1.name());
            taskPerDeveloper.setTaskCount(developerTasksList1.size());
            taskPerDeveloperList.add(taskPerDeveloper);
        }


        return taskPerDeveloperList;
    }
}
