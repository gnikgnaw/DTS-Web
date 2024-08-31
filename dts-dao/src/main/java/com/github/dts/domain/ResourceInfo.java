package com.github.dts.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据集成资源信息表
 * </p>
 *
 * @author dts
 * @since 2024-08-31
 */
@Getter
@Setter
@TableName("resource_info")
public class ResourceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据源名称
     */
    @TableField("resource_name")
    private String resourceName;

    /**
     * 数据源类型
     */
    @TableField("resource_type")
    private Integer resourceType;

    /**
     * 资源连接信息
     */
    @TableField("connection")
    private String connection;

    @TableField("host")
    private String host;

    /**
     * 端口号-可选项
     */
    @TableField("port")
    private Integer port;

    /**
     * 湖仓场景
     */
    @TableField("catalog_name")
    private String catalogName;

    /**
     * 数据库名
     */
    @TableField("database_name")
    private String databaseName;

    /**
     * 表名、topic
     */
    @TableField("table_name")
    private String tableName;

    /**
     * 分区字段
     */
    @TableField("partition_name")
    private String partitionName;

    /**
     * 存储路径
     */
    @TableField("storage_path")
    private String storagePath;

    /**
     * 集群名称
     */
    @TableField("cluster_name")
    private String clusterName;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField("pass_word")
    private String passWord;

    /**
     * 扩展信息
     */
    @TableField("extra_conf")
    private String extraConf;
}
