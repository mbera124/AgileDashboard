package com.smartdashboard.tasks.api;

import com.smartdashboard.infrastracture.common.PaginationUtil;
import com.smartdashboard.infrastracture.utility.PageDetails;
import com.smartdashboard.infrastracture.utility.Pager;
import com.smartdashboard.tasks.data.TasksData;
import com.smartdashboard.tasks.domain.Tasks;
import com.smartdashboard.tasks.domain.TasksRepository;
import com.smartdashboard.tasks.services.TasksService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TasksController {

    private final TasksRepository tasksRepository;
    private final TasksService tasksService;

    @PostMapping("/create-task")
    public ResponseEntity<?> createTasks(@RequestBody List<TasksData> tasksDataList) {
        List<TasksData> tasksDataList1 = tasksService.createTasks(tasksDataList).stream().map(Tasks::toData).collect(Collectors.toList());
        return ResponseEntity.ok(tasksDataList1);
    }
    @GetMapping("/get-tasks")
    public ResponseEntity<?> getAllTasks(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "pageSize", required = false) Integer size) {

            Pageable pageable = PaginationUtil.createPage(page, size);
           List<TasksData> tasksDataList = tasksService.getAllTasks().stream().map(Tasks::toData).collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), tasksDataList.size());
        Page<TasksData> pages = new PageImpl<>(tasksDataList.subList(start, end), pageable, tasksDataList.size());
        Pager<List<TasksData>> pagers = new Pager();
        pagers.setCode("0");
        pagers.setMessage("Success");
        pagers.setContent(pages.getContent());
        PageDetails details = new PageDetails();
        details.setPage(pages.getNumber() + 1);
        details.setPerPage(pages.getSize());
        details.setTotalElements(pages.getTotalElements());
        details.setTotalPage(pages.getTotalPages());
        pagers.setPageDetails(details);
            return ResponseEntity.ok(pagers);
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<?> updateTasks(@PathVariable Long id, @RequestBody TasksData tasksData) {
        TasksData tasksData1 = tasksService.updateTask(id, tasksData).toData();
        return ResponseEntity.ok(tasksData1);
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        tasksRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
