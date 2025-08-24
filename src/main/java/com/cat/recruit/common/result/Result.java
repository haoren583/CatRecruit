package com.cat.recruit.common.result;

import com.cat.recruit.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author cat
 *
 * 返回给前端的结果对象
 */
@Data
@AllArgsConstructor
public class Result {
    private Integer code;
    private String message;
    private Object data;

    /**
     * 用于在success创建Result对象
     */
    public static Result success() {
        return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 用于在success创建Result对象
     */
    public static Result success(Object data) {
        return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 用于在error时创建Result对象
     * 不推荐使用，
     */
    @Deprecated
    public static Result error(ResultEnum resultEnum) {
        return new Result(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

    /**
     * 用于在error时创建Result对象
     */
    public static Result error(BusinessException e) {
        return new Result(e.getCode(), e.getMessage(), null);
    }



}
