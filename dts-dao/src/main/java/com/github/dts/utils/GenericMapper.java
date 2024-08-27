package com.github.dts.utils;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wang
 * @description 描述一下
 * @since 2024/8/28 11:12
 */
@Mapper
public interface GenericMapper<S, T> {
    GenericMapper INSTANCE = Mappers.getMapper(GenericMapper.class);

    T map(S source);
}
