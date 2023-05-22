package com.smartdashboard.tasks.services;

import com.smartdashboard.infrastracture.exception.APIException;
import com.smartdashboard.module.domain.enumeration.ModuleNames;
import com.smartdashboard.sprints.domain.Sprints;
import com.smartdashboard.sprints.domain.SprintsRepository;
import com.smartdashboard.tasks.data.TasksData;
import com.smartdashboard.tasks.domain.*;
import com.smartdashboard.tasks.domain.Enumeration.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;
    private final SprintsRepository sprintsRepository;
    private final DeveloperTasksRepository developerTasksRepository;

    public List<Tasks> createTasks(List<TasksData> tasksData) {
        List<Tasks> tasksList = new ArrayList<>();
        Tasks tasks = new Tasks();
        tasksData.forEach(x -> {
            tasks.setTaskProgress(TaskProgress.valueOf(x.getTaskProgress()));
            tasks.setModuleName(ModuleNames.valueOf(x.getModuleName()));
            Sprints sprints = sprintsRepository.findById(x.getSprintId()).orElseThrow(() -> APIException.notFound("Sprint with id {} not found", x.getSprintId()));
            tasks.setSprints(sprints);
            tasks.setStartDate(x.getStatrDate());
            tasks.setEndDate(x.getEndDate());
            tasks.setActualEndDate(x.getActualEndDate());
            tasks.setRegion(Region.valueOf(x.getRegion()));
            tasks.setTaskName(x.getTaskName());
//save Task
            Tasks tasks1 = tasksRepository.save(tasks);
            tasksList.add(tasks1);
            developerData(x, tasks1);
        });
        return tasksList;
    }
    public List<Tasks> getAllTasks(){
        return tasksRepository.findAll();
    }

    public Tasks updateTask(Long id, TasksData tasksData) {
        Tasks tasks = tasksRepository.findById(id).orElseThrow(() -> APIException.notFound("task with id {} does not exist", id));
        tasks.setTaskName(tasksData.getTaskName());
        tasks.setTaskProgress(TaskProgress.valueOf(tasksData.getTaskProgress()));
        tasks.setModuleName(ModuleNames.valueOf(tasksData.getModuleName()));
        Sprints sprints = sprintsRepository.findById(tasksData.getSprintId()).orElseThrow(() -> APIException.notFound("Sprint with id {} not found", tasksData.getSprintId()));
        tasks.setSprints(sprints);
        tasks.setStartDate(tasksData.getStatrDate());
        tasks.setEndDate(tasksData.getEndDate());
        tasks.setActualEndDate(tasksData.getActualEndDate());
        tasks.setRegion(Region.valueOf(tasksData.getRegion()));
        tasks.setTaskName(tasksData.getTaskName());
//save Task
        Tasks tasks1 = tasksRepository.save(tasks);
        developerData(tasksData, tasks1);
return tasks1;

    }

    private void developerData(TasksData tasksData, Tasks tasks1) {
        if (tasksData.getDeveloperData().isEmpty()) {
            DeveloperTasks developerTasks = new DeveloperTasks();
            developerTasks.setDevelopers(Developers.Kelvin);
            developerTasks.setTasks(tasks1);
            developerTasks.setRole(Roles.ProjectManager);
            developerTasksRepository.save(developerTasks);
        } else {
            tasksData.getDeveloperData().forEach(d->{
                DeveloperTasks developerTasks = new DeveloperTasks();
                developerTasks.setDevelopers(Developers.valueOf(d.getDeveloperName()));
                developerTasks.setTasks(tasks1);
                developerTasks.setRole(Roles.valueOf(d.getRole()));
                developerTasksRepository.save(developerTasks);
            });

        }
    }
}
