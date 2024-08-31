package com.github.dts.domain.connector;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wang
 * @description mysql配置类
 * @since 2024/8/30 15:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MysqlConfig extends DTSConfig{
    private String connection;
    private String host;
    private Integer port;
    private String userName;
    private String passWord;
    private String databaseName;
    private String tableName;

    //CDC配置项
    private String name;


    //其他配置项
}
