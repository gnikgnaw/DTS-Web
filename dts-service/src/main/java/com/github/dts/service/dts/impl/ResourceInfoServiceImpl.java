package com.github.dts.service.dts.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dts.core.http.ApiResponse;
import com.github.dts.domain.ResourceInfo;
import com.github.dts.domain.dto.resourceinfo.ResourceInfoMetaDTO;
import com.github.dts.domain.dto.resourceinfo.ResourceInfoValidateDTO;
import com.github.dts.domain.vo.resourceInfo.ResourceInfoTableVO;
import com.github.dts.enums.connector.ConnectorTypeEnum;
import com.github.dts.mapper.ResourceInfoMapper;
import com.github.dts.service.connector.DTSService;
import com.github.dts.service.dts.ResourceInfoService;
import com.github.dts.utils.DTSBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据集成资源信息表 服务实现类
 * </p>
 *
 * @author dts
 * @since 2024-08-27
 */
@Service
public class ResourceInfoServiceImpl extends ServiceImpl<ResourceInfoMapper, ResourceInfo> implements ResourceInfoService {
    private final Map<String, DTSService> dtsServiceHashMap;

    @Autowired
    public ResourceInfoServiceImpl(Map<String, DTSService> dtsServiceHashMap) {
        this.dtsServiceHashMap = dtsServiceHashMap;
    }

    @Override
    public ApiResponse<Boolean> validateResource(ResourceInfoValidateDTO resourceInfoValidateDTO) {
        DTSService dtsService = dtsServiceHashMap.get(ConnectorTypeEnum.getBeanNameByType(resourceInfoValidateDTO.getResourceType()));
        return dtsService.validateResourceEligibility(resourceInfoValidateDTO);
    }

    @Override
    public List<ResourceInfoTableVO> listResourceTableInfo(ResourceInfoMetaDTO resourceInfoMetaDTO) {
        DTSService dtsService = dtsServiceHashMap.get(ConnectorTypeEnum.getBeanNameByType(resourceInfoMetaDTO.getResourceType()));
        List<ResourceInfo> resourceMetaInfoList = dtsService.listResourceTableInfo(resourceInfoMetaDTO);
        return resourceMetaInfoList.stream().map(x -> DTSBeanUtils.beanTransform(x, ResourceInfoTableVO.class)).collect(Collectors.toList());
    }

    @Override
    public Long saveResourceInfo(ResourceInfoValidateDTO resourceInfoValidateDTO) {
        DTSService dtsService = dtsServiceHashMap.get(ConnectorTypeEnum.getBeanNameByType(resourceInfoValidateDTO.getResourceType()));
        return dtsService.saveResourceInfo(resourceInfoValidateDTO);
    }
}
