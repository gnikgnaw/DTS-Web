package com.github.dts.enums.resource.mysql;

import lombok.Getter;

/**
 * @author wang
 * @description 描述一下
 * @since 2024/8/26 14:46
 */
@Getter
public enum MysqlBinlogStatusEnum {
    /**
     * 开启
     */
    ON("ON", "Binlog关闭"),

    /**
     * 关闭
     */
    OFF("OFF", "Binlog开启");

    private final String value;
    private final String message;

    MysqlBinlogStatusEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }
}
