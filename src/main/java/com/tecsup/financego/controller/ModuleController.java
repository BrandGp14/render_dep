package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.CourseDto;
import com.tecsup.financego.common.type.ModuleDto;
import com.tecsup.financego.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/module")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping("/search")
    public ApiResponse<List<ModuleDto>> search(@RequestParam(value = "description", required = false) String description,
                                               @RequestParam(value = "enabled", required = false) String code,
                                               @RequestParam(value = "duration", required = false) String content) {
        return new ApiResponse<List<ModuleDto>>().toSuccess(moduleService.search(description, code, content));
    }

    @GetMapping("/random")
    public ApiResponse<List<ModuleDto>> getThreeResultRandom() {
        return new ApiResponse<List<ModuleDto>>().toSuccess(moduleService.getFourResultRandom());
    }
}
