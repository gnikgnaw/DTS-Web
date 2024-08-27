package com.github.dts.enums.http;

import lombok.Getter;
import lombok.Setter;

/**
 * ResponseCodeEnum 枚举类，用于定义系统中的响应码和对应的消息。
 * 每个枚举常量包含一个响应码和一个消息，用于描述不同的响应状态。
 *
 * @author wang
 * @since 2024/8/24 18:34
 */
@Getter
public enum ResponseCodeEnum {

    /**
     * 操作成功
     */
    SUCCESS(100, "操作成功"),

    /**
     * 操作失败
     */
    FAILURE(999, "操作失败"),

    /**
     * 服务限流
     */
    RATE_LIMIT(200, "服务开启限流保护,请稍后再试!"),

    /**
     * 服务降级
     */
    SERVICE_DEGRADATION(201, "服务开启降级保护,请稍后再试!"),

    /**
     * 热点参数限流
     */
    HOT_PARAM_LIMIT(202, "热点参数限流,请稍后再试!"),

    /**
     * 系统规则不满足
     */
    SYSTEM_RULE_VIOLATION(203, "系统规则不满足要求,请稍后再试!"),

    /**
     * 授权规则不通过
     */
    AUTHORIZATION_FAILURE(204, "授权规则不通过,请稍后再试!"),

    /**
     * 无访问权限
     */
    ACCESS_DENIED(403, "无访问权限,请联系管理员授予权限"),

    /**
     * 匿名用户访问无权限资源时的异常
     */
    UNAUTHORIZED_ACCESS(401, "匿名用户访问无权限资源时的异常"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(500, "系统异常，请稍后重试");

    private final Integer code;
    private final String message;

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}