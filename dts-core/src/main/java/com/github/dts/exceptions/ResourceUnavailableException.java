package com.github.dts.exceptions;

/**
 * @author wang
 * @description 联通性测试异常
 * @since 2024/8/24 21:22
 */

public class ResourceUnavailableException extends RuntimeException {

    public ResourceUnavailableException(String message) {
        super(message);
    }

    public ResourceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
