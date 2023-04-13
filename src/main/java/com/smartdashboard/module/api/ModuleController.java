package com.smartdashboard.module.api;

import com.smartdashboard.module.domain.enumeration.ModuleNames;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("/module")
public class ModuleController {
    @GetMapping("/get-all-modules")
    public ModuleNames[] getModules() {
        return ModuleNames.values();
    }
}
