package com.cat.recruit.common.exception;

import lombok.Getter;

/**
 * @author cat
 *
 * 业务异常枚举类
 */
@Getter
public enum BusinessExceptionEnum {
    // 业务异常
    UNKNOWN(10000, "未知异常"),
    // 用户不存在
    USER_NOT_EXIST(10001, "用户不存在"),

    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "未找到"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    PASSWORD_ERROR(10002, "密码错误");


    // 错误码
    private final Integer code;
    // 错误信息
    private final String message;

    BusinessExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
