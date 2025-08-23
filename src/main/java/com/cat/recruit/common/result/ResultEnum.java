package com.cat.recruit.common.result;

import lombok.Getter;

/**
 * @author cat
 *
 * 结果枚举类
 */
@Getter
public enum ResultEnum {


    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "未找到"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
