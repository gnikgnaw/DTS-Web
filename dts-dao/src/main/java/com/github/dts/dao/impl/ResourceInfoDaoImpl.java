package com.github.dts.dao.impl;

import com.github.dts.dao.ResourceInfoDao;
import com.github.dts.domain.ResourceInfo;
import com.github.dts.exceptions.ResourcePersistenceException;
import com.github.dts.mapper.ResourceInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 * @author wang
 * @description 资源持久层实现
 * @since 2024/8/27 17:33
 */
@Repository
public class ResourceInfoDaoImpl implements ResourceInfoDao {
    @Resource
    private ResourceInfoMapper resourceInfoMapper;

    @Override
    public Long saveResourceInfo(ResourceInfo resourceInfo) throws ResourcePersistenceException {
        try {
            resourceInfoMapper.insert(resourceInfo);
        } catch (Exception e) {
            throw new ResourcePersistenceException("MySQL资源保存异常", e);
        }
        return resourceInfo.getId();
    }
}
