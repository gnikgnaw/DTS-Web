package com.github.dts.service.connector.mysql;

import com.github.dts.core.http.ApiResponse;
import com.github.dts.domain.ResourceInfo;
import com.github.dts.domain.TaskInfo;
import com.github.dts.domain.connector.DTSConfig;
import com.github.dts.domain.connector.MysqlConfig;
import com.github.dts.domain.dto.resourceinfo.ResourceInfoMetaDTO;
import com.github.dts.domain.dto.resourceinfo.ResourceInfoValidateDTO;
import com.github.dts.enums.http.ResponseCodeEnum;
import com.github.dts.enums.http.ResponseStatusEnum;
import com.github.dts.enums.resource.mysql.MysqlBinlogStatusEnum;
import com.github.dts.enums.resource.mysql.MysqlBinlogTypeEnum;
import com.github.dts.exceptions.QueryResourceInfoMetaException;
import com.github.dts.exceptions.ResourcePersistenceException;
import com.github.dts.exceptions.ResourceUnavailableException;
import com.github.dts.mapper.ResourceInfoMapper;
import com.github.dts.mapper.TaskInfoMapper;
import com.github.dts.service.connector.DTSService;
import com.github.dts.utils.DTSBeanUtils;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wang
 * @description mysql连接器服务类
 * @since 2024/8/24 21:04
 */
@Service
public class MysqlService implements DTSService {
    private static final Logger logger = LoggerFactory.getLogger(MysqlService.class);

    @Resource
    private ResourceInfoMapper resourceInfoMapper;

    @Resource
    private TaskInfoMapper taskInfoMapper;


    private static final String jdbcPrefix = "jdbc:mysql://";

    @Override
    public ApiResponse<Boolean> validateResourceEligibility(ResourceInfoValidateDTO resourceInfoValidateDTO) {
        String jdbcUrl = jdbcPrefix + resourceInfoValidateDTO.getHost() + ":" + resourceInfoValidateDTO.getPort();
        String userName = resourceInfoValidateDTO.getUserName();
        String passWord = resourceInfoValidateDTO.getPassWord();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, userName, passWord)) {
            if (Objects.isNull(connection) || connection.isClosed()) {
                logger.info("连接校验失败");
                return ApiResponse.fail(ResponseStatusEnum.SUCCESS.getCode(), false, "连接校验失败", ResponseCodeEnum.FAILURE.getCode());
            }
            if (!isBinlogEnabled(connection)) {
                logger.info("数据库未开启Binlog");
                return ApiResponse.fail(ResponseStatusEnum.SUCCESS.getCode(), false, "数据库未开启Binlog", ResponseCodeEnum.FAILURE.getCode());
            }
            if (!isBinlogFormatRow(connection)) {
                logger.info("数据库Binlog类型不满足ROW类型");
                return ApiResponse.fail(ResponseStatusEnum.SUCCESS.getCode(), false, "数据库Binlog类型不满足ROW类型", ResponseCodeEnum.FAILURE.getCode());
            }
        } catch (SQLException e) {
            throw new ResourceUnavailableException("数据源连接异常", e);
        }
        return ApiResponse.success(true);
    }

    @Override
    public List<ResourceInfo> listResourceTableInfo(ResourceInfoMetaDTO resourceInfoMetaDTO) {
        String jdbcUrl = jdbcPrefix + resourceInfoMetaDTO.getHost() + ":" + resourceInfoMetaDTO.getPort();
        String userName = resourceInfoMetaDTO.getUserName();
        String passWord = resourceInfoMetaDTO.getPassWord();
        String databaseName = resourceInfoMetaDTO.getDatabaseName();
        String tbName = resourceInfoMetaDTO.getTableName();
        List<ResourceInfo> resourceInfoList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, userName, passWord)) {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet resultSet = metaData.getTables(databaseName, null, "%" + tbName + "%", new String[]{"TABLE"})) {
                while (resultSet.next()) {
                    String tableName = resultSet.getString("TABLE_NAME");
                    ResourceInfo resourceInfo = new ResourceInfo();
                    resourceInfo.setTableName(tableName);
                    resourceInfoList.add(resourceInfo);
                }
            }
        } catch (SQLException e) {
            throw new QueryResourceInfoMetaException("获取表信息失败", e);
        }
        return resourceInfoList;
    }

    @Override
    public Long saveResourceInfo(ResourceInfoValidateDTO resourceInfoValidateDTO) {
        ResourceInfo resourceInfo = DTSBeanUtils.beanTransform(resourceInfoValidateDTO, ResourceInfo.class);
        try {
            resourceInfoMapper.insert(resourceInfo);
        } catch (Exception e) {
            throw new ResourcePersistenceException("MySQL资源保存异常", e);
        }
        return resourceInfo.getId();
    }

    @Override
    public DTSConfig initSourceConfig(Long taskId) {
        TaskInfo taskInfo = taskInfoMapper.selectById(taskId);
        String sourceIds = taskInfo.getSourceIds();
        List<Long> sourceIdList = Arrays.stream(sourceIds.split(",")).map(Long::parseLong).toList();
        List<ResourceInfo> resourceInfos = resourceInfoMapper.selectBatchIds(sourceIdList);
        MysqlConfig mysqlConfig = new MysqlConfig();
        mysqlConfig.setHost(resourceInfos.get(0).getHost());
        mysqlConfig.setPort(resourceInfos.get(0).getPort());
        mysqlConfig.setUserName(resourceInfos.get(0).getUserName());
        mysqlConfig.setPassWord(resourceInfos.get(0).getPassWord());
        mysqlConfig.setDatabaseName(resourceInfos.stream().map(ResourceInfo::getDatabaseName).collect(Collectors.joining(",")));
        mysqlConfig.setTableName(resourceInfos.stream().map(resourceInfo -> resourceInfo.getDatabaseName() + "." + resourceInfo.getTableName()).collect(Collectors.joining(",")));
        return mysqlConfig;
    }

    @Override
    public DTSConfig initSinkConfig(Long taskId) {
        return null;
    }

    private boolean isBinlogEnabled(Connection connection) throws SQLException {
        String query = "SHOW VARIABLES LIKE 'log_bin'";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String value = resultSet.getString("Value");
                return MysqlBinlogStatusEnum.ON.getValue().equalsIgnoreCase(value);
            }
        }
        return false;
    }

    private boolean isBinlogFormatRow(Connection connection) throws SQLException {
        String query = "SHOW VARIABLES LIKE 'binlog_format'";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String value = resultSet.getString("Value");
                return MysqlBinlogTypeEnum.ROW.getValue().equalsIgnoreCase(value);
            }
        }
        return false;
    }
}
