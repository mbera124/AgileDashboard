package com.react.reactspring.outlet.api;

import com.react.reactspring.outlet.data.OutletData;
import com.react.reactspring.outlet.domain.Outlet;
import com.react.reactspring.outlet.service.OutletService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("outlets")
@RequiredArgsConstructor
public class OutletController {
    private final OutletService outletService;

    @GetMapping("/fetch-all")
    public ResponseEntity<?> fetchAllOutlets (
            @RequestParam(value = "search", required = false) String search
    ) {
        List<OutletData> list = outletService.getAllOutlets(search).stream()
                .map(x->x.toData())
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @PostMapping("/create-outlet")
    public ResponseEntity<?> createOutlet (
            @RequestBody OutletData outletData
    ) {
        OutletData data = outletService.createOutlet(outletData);

        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOutlet (
            @PathVariable("id") Long id
    ) {
        outletService.deleteOutlet(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOutlet (
        @PathVariable("id") Long id,
        @RequestBody OutletData outletData
    ) {
        OutletData savedOutletData = outletService.updateOutlet (id, outletData);

        return ResponseEntity.ok(savedOutletData);
    }

}
