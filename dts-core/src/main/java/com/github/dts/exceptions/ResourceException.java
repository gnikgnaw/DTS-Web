package com.github.dts.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author wang
 * @description 联通性测试异常
 * @since 2024/8/24 21:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceException extends RuntimeException {
    public Integer code;
    public ResourceException(String message,Integer code) {
        super(message);
        this.code = code;
    }

    public ResourceException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }
}
