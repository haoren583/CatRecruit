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
    UNKNOWN("000000", "未知异常");
    // 错误码
    private final String code;
    // 错误信息
    private final String message;

    BusinessExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
