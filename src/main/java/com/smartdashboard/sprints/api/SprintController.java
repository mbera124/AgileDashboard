package com.smartdashboard.sprints.api;

import com.smartdashboard.infrastracture.common.PaginationUtil;
import com.smartdashboard.infrastracture.utility.PageDetails;
import com.smartdashboard.infrastracture.utility.Pager;
import com.smartdashboard.sprints.data.SprintData;
import com.smartdashboard.sprints.domain.Sprints;
import com.smartdashboard.sprints.domain.SprintsRepository;
import com.smartdashboard.sprints.service.SprintService;
import com.smartdashboard.tasks.data.TasksData;
import com.smartdashboard.tasks.domain.Tasks;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("/sprints")
public class SprintController {
private final SprintService sprintService;
private final SprintsRepository sprintsRepository;


        @PostMapping("/create-sprint")
        public ResponseEntity<?> createSprint(@RequestBody SprintData sprintData) {
            SprintData sprintData1  = sprintService.createSprint(sprintData).toData();
            return ResponseEntity.ok(sprintData1);
        }
    @GetMapping("/get-sprints")
    public ResponseEntity<?> getAllSprints(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "pageSize", required = false) Integer size) {

        Pageable pageable = PaginationUtil.createPage(page, size);
        List<SprintData> sprintData = sprintService.getAllSprints().stream().map(Sprints::toData).collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sprintData.size());
        Page<SprintData> pages = new PageImpl<>(sprintData.subList(start, end), pageable, sprintData.size());
        Pager<List<SprintData>> pagers = new Pager();
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
    @PutMapping("/update-sprint/{id}")
    public ResponseEntity<?> updateSprint(@PathVariable Long id, @RequestBody SprintData sprintData) {
        SprintData sprintData1 = sprintService.updateSprint(id, sprintData).toData();
        return ResponseEntity.ok(sprintData1);
    }

    @DeleteMapping("/delete-sprint/{id}")
    public ResponseEntity<?> deleteSprint(@PathVariable Long id) {
        sprintsRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
