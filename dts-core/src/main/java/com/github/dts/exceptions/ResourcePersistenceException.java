package com.github.dts.exceptions;

/**
 * @author wang
 * @description 联通性测试异常
 * @since 2024/8/24 21:22
 */

public class ResourcePersistenceException extends RuntimeException {

    public ResourcePersistenceException(String message) {
        super(message);
    }

    public ResourcePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
