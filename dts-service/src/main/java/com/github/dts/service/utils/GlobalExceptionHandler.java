package com.github.dts.service.utils;

import com.github.dts.core.http.ApiResponse;
import com.github.dts.enums.http.ResponseCodeEnum;
import com.github.dts.enums.http.ResponseStatusEnum;
import com.github.dts.exceptions.QueryResourceInfoMetaException;
import com.github.dts.exceptions.ResourceUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wang
 * @description 全局异常捕获类
 * @since 2024/8/24 21:20
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 处理联通性异常
     *
     * @param ex 捕获的联通性异常
     * @return 错误响应
     */
    @ExceptionHandler(ResourceUnavailableException.class)
    @ResponseBody
    public ApiResponse<String> handleConnectivityException(ResourceUnavailableException ex) {
        logger.error("联通性测试异常: {}", ex.getMessage());
        return createErrorResponse(ex.getMessage());
    }

    /**
     * 处理数据源元数据信息查询异常
     *
     * @param ex 捕获的元数据信息查询异常
     * @return 错误响应
     */
    @ExceptionHandler(QueryResourceInfoMetaException.class)
    @ResponseBody
    public ApiResponse<String> handleQueryResourceInfoMetaException(QueryResourceInfoMetaException ex) {
        logger.error("元数据信息查询异常: {}", ex.getMessage());
        return createErrorResponse(ex.getMessage());
    }


    /**
     * 创建错误响应
     *
     * @param message 错误信息
     * @return 错误响应
     */
    private ApiResponse<String> createErrorResponse(String message) {
        return ApiResponse.error(ResponseStatusEnum.SUCCESS.getCode(), message, ResponseCodeEnum.FAILURE.getCode());
    }
}
