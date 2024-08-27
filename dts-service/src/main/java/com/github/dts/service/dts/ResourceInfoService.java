package com.github.dts.service.dts;

import com.github.dts.core.http.ApiResponse;
import com.github.dts.domain.ResourceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.dts.domain.dto.ResourceInfo.ResourceInfoMetaDTO;
import com.github.dts.domain.dto.ResourceInfo.ResourceInfoValidateDTO;
import com.github.dts.domain.vo.resourceInfo.ResourceInfoTableVO;

import java.util.List;

/**
 * <p>
 * 数据集成资源信息表 服务类
 * </p>
 *
 * @author dts
 * @since 2024-08-27
 */
public interface ResourceInfoService extends IService<ResourceInfo> {

    ApiResponse<Boolean> validateResource(ResourceInfoValidateDTO resourceInfoValidateDTO);

    List<ResourceInfoTableVO> listResourceTableInfo(ResourceInfoMetaDTO resourceInfoMetaDTO);

    Long saveResourceInfo(ResourceInfoValidateDTO resourceInfoValidateDTO);
}
