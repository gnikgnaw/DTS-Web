package com.github.dts.service.dts.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dts.domain.TaskInfo;
import com.github.dts.domain.dto.taskinfo.TaskInfoAddDTO;
import com.github.dts.mapper.TaskInfoMapper;
import com.github.dts.service.dts.TaskInfoService;

import com.github.dts.utils.DTSBeanUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dts
 * @since 2024-08-24
 */
@Service
public class TaskInfoServiceImpl extends ServiceImpl<TaskInfoMapper, TaskInfo> implements TaskInfoService {

    @Resource
    private TaskInfoMapper taskInfoMapper;
    /**
     * 新增任务
     */
    @Override
    public Long addNewTask(TaskInfoAddDTO taskInfoAddDTO) {
        TaskInfo taskInfo = DTSBeanUtils.beanTransform(taskInfoAddDTO, TaskInfo.class);
        taskInfoMapper.insert(taskInfo);
        return taskInfo.getId();
    }
}
