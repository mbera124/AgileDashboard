package com.smartdashboard.dashboard.api;

import com.smartdashboard.dashboard.data.AtRiskData;
import com.smartdashboard.dashboard.data.TaskPerDeveloper;
import com.smartdashboard.dashboard.service.DashboardService;
import com.smartdashboard.tasks.domain.Enumeration.Developers;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/get-risky-tasks")
    public ResponseEntity<?> getAtRiskTasks(@RequestParam(value = "date") String date) {
        LocalDate localDate=LocalDate.parse(date);
        List<AtRiskData> atRiskData = dashboardService.findAtRiskTasks(localDate);
        return ResponseEntity.ok(atRiskData);
    }
    @GetMapping("/get-tasks-per-developer")
    public ResponseEntity<?> getTaskPerDeveloper(@RequestParam(value = "developer",required = false) Developers developer,
                                                 @RequestParam(value = "date",required = false) String date) {
        LocalDate localDate=null;
       if (date!=null){
           localDate=LocalDate.parse(date);
       }
        List<TaskPerDeveloper> taskPerDevelopers = dashboardService.findTaskPerDevelopers(developer,localDate);
        return ResponseEntity.ok(taskPerDevelopers);
    }
}
