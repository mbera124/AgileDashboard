package com.smartdashboard.sprints.service;

import com.smartdashboard.infrastracture.exception.APIException;
import com.smartdashboard.module.domain.enumeration.ModuleNames;
import com.smartdashboard.sprints.data.SprintData;
import com.smartdashboard.sprints.domain.Sprints;
import com.smartdashboard.sprints.domain.SprintsRepository;
import com.smartdashboard.tasks.domain.DeveloperTasksRepository;
import com.smartdashboard.tasks.domain.TasksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class SprintService {
    private final TasksRepository tasksRepository;
    private final SprintsRepository sprintsRepository;
    private final DeveloperTasksRepository developerTasksRepository;

    public Sprints createSprint(SprintData sprintData) {

        Sprints sprints = new Sprints();
        sprints.setSprintMonth(sprintData.getSprintMonth());
        sprints.setTaskcount(sprintData.getTaskcount());
        sprints.setSprintName(sprintData.getSprintName());
        sprints.setStartDate(sprintData.getStartDate());
        sprints.setEndDate(sprintData.getEndDate());
        sprints.setActualCompletionDate(sprintData.getActualCompletionDate());
        sprints.setModule(ModuleNames.valueOf(sprintData.getModule()));
        return sprintsRepository.save(sprints);
    }
    public List<Sprints> getAllSprints(){
        return sprintsRepository.findAll();
    }
    public Sprints updateSprint(Long id, SprintData sprintData) {
        Sprints sprints = sprintsRepository.findById(id).orElseThrow(() -> APIException.notFound("sprint with id {} does not exist", id));
        sprints.setSprintMonth(sprintData.getSprintMonth());
        sprints.setTaskcount(sprintData.getTaskcount());
        sprints.setSprintName(sprintData.getSprintName());
        sprints.setStartDate(sprintData.getStartDate());
        sprints.setEndDate(sprintData.getEndDate());
        sprints.setActualCompletionDate(sprintData.getActualCompletionDate());
        sprints.setModule(ModuleNames.valueOf(sprintData.getModule()));
        return sprintsRepository.save(sprints);

    }


}
