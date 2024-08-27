package com.github.dts.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据集成任务配置表
 * </p>
 *
 * @author dts
 * @since 2024-08-27
 */
@Getter
@Setter
@TableName("task_info")
public class TaskInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = -5944891006959259306L;

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
     * 引擎任务表主键
     */
    @TableField("engine_task_id")
    private String engineTaskId;

    /**
     * 任务表配置信息
     */
    @TableField("task_info_extra_conf")
    private String taskInfoExtraConf;

    /**
     * 创建时间
     */
    @TableField("ctime")
    private LocalDate ctime;

    /**
     * 修改时间
     */
    @TableField("mtime")
    private LocalDate mtime;
}
