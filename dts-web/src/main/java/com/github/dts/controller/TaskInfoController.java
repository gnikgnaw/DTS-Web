package com.github.dts.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dts
 * @since 2024-08-24
 */
@RestController
@RequestMapping("/taskInfo")
@Tag(name = "任务管理", description = "提供示例内容展示SpringDoc集成效果")
public class TaskInfoController {

}
