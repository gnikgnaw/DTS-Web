package com.github.dts.enums.resource;

import lombok.Getter;

/**
 * @author wang
 * @description 用于判定与各数据源的连通性
 * @since 2024/8/24 20:59
 */
@Getter
public enum ResourceResponseCodeEnum {

    CONNECTIVITY_SUCCESS(1001, "联通性测试成功"),
    CONNECTIVITY_FAILURE(1002, "联通性测试失败"),
    LIST_TABLE_ERROR(1003, "获取表信息失败"),
    SAVE_RESOURCE_INFO_ERROR(1004, "获取表信息失败");

    private final Integer code;
    private final String message;

    ResourceResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
