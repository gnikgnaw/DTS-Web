package com.github.dts.service.dts;

import com.github.dts.domain.TaskInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.dts.domain.dto.taskinfo.TaskInfoAddDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dts
 * @since 2024-08-24
 */
public interface TaskInfoService extends IService<TaskInfo> {
    /**
     * 新增任务
     */
    Long addNewTask(TaskInfoAddDTO taskInfoAddDTO);
}
