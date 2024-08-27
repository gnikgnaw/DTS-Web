package com.github.dts.core.http;

import com.github.dts.enums.http.ResponseCodeEnum;
import com.github.dts.enums.http.ResponseStatusEnum;
import lombok.Data;

/**
 * @author wang
 * @description 描述一下
 * @since 2024/8/24 18:31
 */
@Data
public class ApiResponse<T> {
    private Integer status;
    private String message;
    private T data;
    private Integer code;

    public ApiResponse(Integer status, String message, T data, Integer code) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResponseStatusEnum.SUCCESS.getCode(), "Success", data, ResponseCodeEnum.SUCCESS.getCode());
    }

    public static <T> ApiResponse<T> error(Integer status, String message, Integer code) {
        return new ApiResponse<>(status, message, null, code);
    }

    public static <T> ApiResponse<T> fail(Integer status, T data, String message, Integer code) {
        return new ApiResponse<>(status, message, data, code);
    }
}