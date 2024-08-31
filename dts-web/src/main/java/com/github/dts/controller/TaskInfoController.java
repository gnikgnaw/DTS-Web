package com.github.dts.controller;

import com.github.dts.core.http.ApiResponse;
import com.github.dts.domain.TaskInfo;
import com.github.dts.domain.dto.taskinfo.TaskInfoAddDTO;
import com.github.dts.service.dts.TaskInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dts
 * @since 2024-08-24
 */
@RestController
@RequestMapping("/taskInfo")
@Tag(name = "任务管理", description = "提供示例内容展示SpringDoc集成效果")
public class TaskInfoController {

    @Resource
    private TaskInfoService taskInfoService;

    /**
     * 新增任务
     */
    @PostMapping("/addTask")
    public ApiResponse<Long> addTask(@RequestBody TaskInfoAddDTO taskInfoAddDTO) {
        Long id = taskInfoService.addNewTask(taskInfoAddDTO);
        return ApiResponse.success(id);
    }

}
