package com.github.dts.service.connector.mysql;

import com.github.dts.core.http.ApiResponse;
import com.github.dts.dao.ResourceInfoDao;
import com.github.dts.domain.ResourceInfo;
import com.github.dts.domain.dto.ResourceInfo.ResourceInfoMetaDTO;
import com.github.dts.domain.dto.ResourceInfo.ResourceInfoValidateDTO;
import com.github.dts.enums.http.ResponseCodeEnum;
import com.github.dts.enums.http.ResponseStatusEnum;
import com.github.dts.enums.resource.mysql.MysqlBinlogStatusEnum;
import com.github.dts.enums.resource.mysql.MysqlBinlogTypeEnum;
import com.github.dts.exceptions.QueryResourceInfoMetaException;
import com.github.dts.exceptions.ResourceUnavailableException;
import com.github.dts.service.connector.DTSService;
import com.github.dts.utils.MapStructUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wang
 * @description mysql连接器服务类
 * @since 2024/8/24 21:04
 */
@Service
public class MysqlService implements DTSService {
    private static final Logger logger = LoggerFactory.getLogger(MysqlService.class);

    @Resource
    private ResourceInfoDao resourceInfoDao;

    private static final String jdbcPrefix = "jdbc:mysql://";

    @Override
    public ApiResponse<Boolean> validateResourceEligibility(ResourceInfoValidateDTO resourceInfoValidateDTO) {
        String jdbcUrl = jdbcPrefix + resourceInfoValidateDTO.getHost()+":"+ resourceInfoValidateDTO.getPort();
        String userName = resourceInfoValidateDTO.getUserName();
        String passWord = resourceInfoValidateDTO.getPassWord();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, userName, passWord)) {
            if (Objects.isNull(connection) || connection.isClosed()) {
                logger.info("连接校验失败");
                return ApiResponse.fail(ResponseStatusEnum.SUCCESS.getCode(), false,"连接校验失败", ResponseCodeEnum.FAILURE.getCode());
            }
            if (!isBinlogEnabled(connection)) {
                logger.info("数据库未开启Binlog");
                return ApiResponse.fail(ResponseStatusEnum.SUCCESS.getCode(), false,"数据库未开启Binlog", ResponseCodeEnum.FAILURE.getCode());
            }
            if (!isBinlogFormatRow(connection)) {
                logger.info("数据库Binlog类型不满足ROW类型");
                return ApiResponse.fail(ResponseStatusEnum.SUCCESS.getCode(), false,"数据库Binlog类型不满足ROW类型", ResponseCodeEnum.FAILURE.getCode());
            }
        } catch (SQLException e) {
            throw new ResourceUnavailableException("数据源连接异常", e);
        }
        return ApiResponse.success(true);
    }

    @Override
    public List<ResourceInfo> listResourceTableInfo(ResourceInfoMetaDTO resourceInfoMetaDTO) {
        String jdbcUrl = jdbcPrefix + resourceInfoMetaDTO.getHost()+":"+ resourceInfoMetaDTO.getPort();
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
        ResourceInfo resourceInfo = MapStructUtil.beanTransform(resourceInfoValidateDTO, ResourceInfo.class);
        return resourceInfoDao.saveResourceInfo(resourceInfo);
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
