package com.github.dts.enums.http;

import lombok.Getter;

/**
 * ResponseCodeEnum 枚举类，用于定义系统中的响应码和对应的消息。
 * 每个枚举常量包含一个响应码和一个消息，用于描述不同的响应状态。
 *
 * @author wang
 * @since 2024/8/24 18:34
 */
@Getter
public enum ResponseStatusEnum {


    SUCCESS(200, "请求成功。一般用于GET与POST请求"),
    CREATED(201, "已创建。成功请求并创建了新的资源"),
    ACCEPTED(202, "已接受。已经接受请求，但未处理完成"),
    BAD_REQUEST(400, "客户端错误，请求包含语法错误或无法完成请求"),
    UNAUTHORIZED(401, "请求要求用户的身份认证"),
    FORBIDDEN(403, "服务器理解请求客户端的请求，但是拒绝执行此请求"),
    NOT_FOUND(404, "服务器无法根据客户端的请求找到资源（网页）"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误，无法完成请求"),
    NOT_IMPLEMENTED(501, "服务器不支持请求的功能，无法完成请求"),
    SERVICE_UNAVAILABLE(503, "由于超载或系统维护，服务器暂时的无法处理客户端的请求");

    private final Integer code;
    private final String message;

    ResponseStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}