package com.github.dts.service.dts.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dts.domain.TaskInfo;
import com.github.dts.mapper.TaskInfoMapper;
import com.github.dts.service.dts.TaskInfoService;

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

}
