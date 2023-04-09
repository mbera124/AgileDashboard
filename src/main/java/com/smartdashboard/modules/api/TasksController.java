package com.smartdashboard.modules.api;

import com.smartdashboard.modules.data.TasksData;
import com.smartdashboard.modules.domain.Enumeration.ModuleNames;
import com.smartdashboard.modules.domain.Tasks;
import com.smartdashboard.modules.domain.TasksRepository;
import com.smartdashboard.modules.services.TasksService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TasksController {

        private final TasksRepository tasksRepository;
        private final TasksService tasksService;

        @GetMapping("/get-all-modules")
        public ModuleNames[] getModules() {
            return ModuleNames.values();
        }

//        @GetMapping("/{id}")
//        public Modules getModule(@PathVariable Long id) {
//            return modulesRepository.findById(id).orElseThrow(RuntimeException::new);
//        }
//
        @PostMapping("/create")
        public ResponseEntity<?> createTasks(@RequestBody List<TasksData> tasksDataList) throws URISyntaxException {
            List<TasksData> tasksDataList1 = tasksService.createTasks(tasksDataList).stream().map(Tasks::toData).collect(Collectors.toList());
            return ResponseEntity.ok(tasksDataList1);
        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<?> updateModules(@PathVariable Long id, @RequestBody Modules modules) {
//            Modules modules1 = modulesRepository.findById(id).orElseThrow(RuntimeException::new);
//            modules1.setModuleName(modules.getModuleName());
//            modulesRepository.save(modules1);
//
//            return ResponseEntity.ok(modules1);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deleteModules(@PathVariable Long id) {
//            modulesRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        }
}
