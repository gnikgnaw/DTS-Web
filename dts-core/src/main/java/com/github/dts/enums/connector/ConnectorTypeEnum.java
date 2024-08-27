package com.github.dts.enums.connector;

import lombok.Getter;

/**
 * @author wang
 * @description 连接器映射实例名称
 * @since 2024/8/24 21:41
 */
@Getter
public enum ConnectorTypeEnum {
    MYSQL(1, "mysqlService");

    private final Integer resourceType;
    private final String beanName;

    ConnectorTypeEnum(Integer resourceType, String beanName) {
        this.resourceType = resourceType;
        this.beanName = beanName;
    }

    /**
     * 通过code返回对应的beanName
     *
     * @param code 连接器类型的代码
     * @return 对应的beanName，如果没有找到则返回null
     */
    public static String getBeanNameByType(Integer code) {
        for (ConnectorTypeEnum type : ConnectorTypeEnum.values()) {
            if (type.getResourceType().equals(code)) {
                return type.getBeanName();
            }
        }
        return "";
    }

}
