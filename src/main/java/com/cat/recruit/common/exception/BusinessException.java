package com.cat.recruit.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author cat
 *
 * 自定义业务异常类
 */
@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    // 错误码
    private final String code;
    // 错误信息
    private final String message;

    /**
     * 工厂方法，用于创建业务异常对象
     * @param businessExceptionEnum 业务异常枚举
     */
    public static BusinessException bizException(BusinessExceptionEnum businessExceptionEnum) {
        return new BusinessException(businessExceptionEnum.getCode(), businessExceptionEnum.getMessage());
    }
}
