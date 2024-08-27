package com.github.dts.controller;

import com.github.dts.core.http.ApiResponse;
import com.github.dts.domain.dto.ResourceInfo.ResourceInfoMetaDTO;
import com.github.dts.domain.dto.ResourceInfo.ResourceInfoValidateDTO;
import com.github.dts.domain.vo.resourceInfo.ResourceInfoTableVO;
import com.github.dts.service.dts.ResourceInfoService;
import com.github.dts.utils.DTSBeanUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据集成资源信息表 前端控制器
 * </p>
 *
 * @author dts
 * @since 2024-08-24
 */
@RestController
@RequestMapping("/resourceInfo")
@Tag(name = "资源管理", description = "提供示例内容展示SpringDoc集成效果")
public class ResourceInfoController {

    @Resource
    private ResourceInfoService resourceInfoService;

    @GetMapping("/test")
    public ApiResponse<Boolean> test() {
        return ApiResponse.success(true);
    }

    @PostMapping("/validateResource")
    public ApiResponse<Boolean> validateResource(@RequestBody ResourceInfoValidateDTO resourceInfoValidateDTO) {
        return resourceInfoService.validateResource(resourceInfoValidateDTO);
    }

    @PostMapping("/listResourceTableInfo")
    public ApiResponse<List<ResourceInfoTableVO>> listResourceTableInfo(@RequestBody ResourceInfoMetaDTO resourceInfoMetaDTO) {
        List<ResourceInfoTableVO> resourceInfoTableVOS = resourceInfoService.listResourceTableInfo(resourceInfoMetaDTO);
        return ApiResponse.success(resourceInfoTableVOS);
    }

    @PostMapping("/saveResourceInfo")
    public ApiResponse<Long> saveResourceInfo(@RequestBody ResourceInfoValidateDTO resourceInfoValidateDTO) {
        return ApiResponse.success(resourceInfoService.saveResourceInfo(resourceInfoValidateDTO));
    }

}
