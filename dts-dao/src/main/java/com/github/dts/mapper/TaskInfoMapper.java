package com.github.dts.mapper;

import com.github.dts.domain.TaskInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据集成任务配置表 Mapper 接口
 * </p>
 *
 * @author dts
 * @since 2024-08-27
 */
@Mapper
public interface TaskInfoMapper extends BaseMapper<TaskInfo> {

}
