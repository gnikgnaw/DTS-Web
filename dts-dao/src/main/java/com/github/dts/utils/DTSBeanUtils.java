package com.github.dts.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author wang
 * @description 描述一下
 * @since 2024/8/27 19:42
 */
public class DTSBeanUtils<source, target> {
    public static <target> target beanTransform(Object sourceEntity, Class<target> doClass) {
        if (sourceEntity == null) {
            return null;
        }
        if (doClass == null) {
            return null;
        }
        try {
            target newInstance = doClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(sourceEntity, newInstance);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }
}