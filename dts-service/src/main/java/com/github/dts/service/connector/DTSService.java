package com.github.dts.service.connector;

import com.github.dts.core.http.ApiResponse;
import com.github.dts.domain.dto.ResourceInfo.ResourceInfoMetaDTO;
import com.github.dts.domain.dto.ResourceInfo.ResourceInfoValidateDTO;
import com.github.dts.domain.ResourceInfo;

import java.util.List;

/**
 * @author wang
 * @description 连接器抽象接口
 * @since 2024/8/24 21:02
 */
public interface DTSService {
    /**
     * 校验资源是否满足接入条件
     */
    ApiResponse<Boolean> validateResourceEligibility(ResourceInfoValidateDTO resourceInfoValidateDTO);

    /**
     * 获取连接器元数据信息,库表信息
     */
     List<ResourceInfo> listResourceTableInfo(ResourceInfoMetaDTO resourceInfoMetaDTO);

    Long saveResourceInfo(ResourceInfoValidateDTO resourceInfoValidateDTO);
}
