package com.github.dts.enums.engine;

import com.github.dts.enums.connector.ConnectorTypeEnum;

/**
 * @author wang
 * @description 描述一下
 * @since 2024/9/1 11:17
 */
public enum EngineTypeEnum {
    FLINK_CDC(1, "FlinkCDC"),
    SEATUNNEL(2, "Seatunnel");


    private final Integer engineType;
    private final String engineName;

    EngineTypeEnum(Integer engineType, String engineName) {
        this.engineType = engineType;
        this.engineName = engineName;
    }

    public static EngineTypeEnum getEngineNameByType(Integer engineType) {
        for (EngineTypeEnum type : EngineTypeEnum.values()) {
            if (type.engineType.equals(engineType)) {
                return type;
            }
        }
        return null;
    }
}
