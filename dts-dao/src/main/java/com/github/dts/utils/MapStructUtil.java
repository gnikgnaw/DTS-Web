package com.github.dts.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author wang
 * @description 类型转换
 * @since 2024/8/28 10:34
 */

public class MapStructUtil {
    public static <S, T> T beanTransform(S sourceEntity, Class<T> targetClass) {
        if (sourceEntity == null || targetClass == null) {
            return null;
        }
        GenericMapper<S, T> mapper = (GenericMapper<S, T>) GenericMapper.INSTANCE;
        return mapper.map(sourceEntity);
    }
}
