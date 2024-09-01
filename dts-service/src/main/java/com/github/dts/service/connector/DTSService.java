package com.github.dts.service.connector;

import com.github.dts.core.http.ApiResponse;
import com.github.dts.domain.connector.DTSConfig;
import com.github.dts.domain.dto.resourceinfo.ResourceInfoMetaDTO;
import com.github.dts.domain.dto.resourceinfo.ResourceInfoValidateDTO;
import com.github.dts.domain.ResourceInfo;

import java.util.List;

/**
 * @author wang
 * @description 连接器抽象接口
 * @since 2024/8/24 21:02
 */
public interface DTSService {
    ApiResponse<Boolean> validateResourceEligibility(ResourceInfoValidateDTO resourceInfoValidateDTO);

    List<ResourceInfo> listResourceTableInfo(ResourceInfoMetaDTO resourceInfoMetaDTO);

    Long saveResourceInfo(ResourceInfoValidateDTO resourceInfoValidateDTO);

    DTSConfig initSourceConfig(Long taskId);

    DTSConfig initSourceConfigForFlinkCDC(List<ResourceInfo> resourceInfos);

    DTSConfig initSinkConfig(Long taskId);

    DTSConfig initSinkConfigForFlinkCDC(List<ResourceInfo> resourceInfos);
}
