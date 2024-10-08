package com.github.dts.domain.dto.taskinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wang
 * @description 描述一下
 * @since 2024/8/30 16:40
 */
@Data
public class TaskInfoAddDTO {
    private String taskName;
    private Integer taskType;
    private Integer sourceType;
    private String sourceIds;
    private Integer sinkType;
    private String sinkIds;
    private String taskInfoExtraConf;
    @JsonFormat
    private LocalDateTime ctime;
    @JsonFormat
    private LocalDateTime mtime;
}
