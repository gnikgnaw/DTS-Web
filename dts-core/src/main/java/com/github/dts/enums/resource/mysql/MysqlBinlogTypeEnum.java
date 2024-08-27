package com.github.dts.enums.resource.mysql;

import lombok.Getter;

/**
 * @author wang
 * @description 描述一下
 * @since 2024/8/26 14:46
 */
@Getter
public enum MysqlBinlogTypeEnum {
    ROW("ROW", "ROW类型");

    private final String value;
    private final String message;

    MysqlBinlogTypeEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }
}
