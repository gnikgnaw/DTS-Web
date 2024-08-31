package com.github.dts.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据集成任务配置表
 * </p>
 *
 * @author dts
 * @since 2024-08-31
 */
@Getter
@Setter
@TableName("task_info")
public class TaskInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务名称
     */
    @TableField("task_name")
    private String taskName;

    /**
     * 任务类型
     */
    @TableField("task_type")
    private Integer taskType;

    /**
     * 数据来源类型
     */
    @TableField("source_type")
    private Integer sourceType;

    /**
     * 数据来源资源id
     */
    @TableField("source_ids")
    private String sourceIds;

    /**
     * 数据去向类型
     */
    @TableField("sink_type")
    private Integer sinkType;

    /**
     * 数据去向资源id
     */
    @TableField("sink_ids")
    private String sinkIds;

    /**
     * 任务表配置信息
     */
    @TableField("task_info_extra_conf")
    private String taskInfoExtraConf;

    /**
     * 创建时间
     */
    @TableField("ctime")
    private LocalDateTime ctime;

    /**
     * 修改时间
     */
    @TableField("mtime")
    private LocalDateTime mtime;
}
