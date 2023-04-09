package com.smartdashboard.modules.services;

import com.smartdashboard.modules.data.TasksData;
import com.smartdashboard.modules.domain.Enumeration.ModuleNames;
import com.smartdashboard.modules.domain.Enumeration.TaskProgress;
import com.smartdashboard.modules.domain.Tasks;
import com.smartdashboard.modules.domain.TasksRepository;
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
    public List<Tasks> createTasks(List<TasksData> tasksData){
        List<Tasks> tasksList=new ArrayList<>();
        Tasks tasks=new Tasks();
        tasksData.forEach(x->{
tasks.setTaskProgress(TaskProgress.valueOf(x.getTaskProgress()));
            tasks.setModuleName(ModuleNames.valueOf(x.getModuleName()));
            tasks.setTaskCount(x.getTaskCount());
            tasks.setTaskName(x.getTaskName());
            tasks.setDevDuration(x.getDevDuration());
            tasksList.add(tasks);
        });
return tasksRepository.saveAll(tasksList);
    }
}
