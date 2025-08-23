package com.cat.recruit.common.result;

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
     * 用于在error创建Result对象
     */
    public static Result error(ResultEnum resultEnum) {
        return new Result(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

}
