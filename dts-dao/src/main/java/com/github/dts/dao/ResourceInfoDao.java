package com.github.dts.dao;

import com.github.dts.domain.ResourceInfo;
import com.github.dts.exceptions.ResourcePersistenceException;

/**
 * @author wang
 * @description 资源持久层接口
 * @since 2024/8/25 20:53
 */
public interface ResourceInfoDao {

    Long saveResourceInfo(ResourceInfo resourceInfo) throws ResourcePersistenceException;
}
