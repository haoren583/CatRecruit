package com.cat.recruit.common.exception;

import com.cat.recruit.common.result.Result;
import com.cat.recruit.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cat
 *
 * 全局异常处理类
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerGlobal {
    // 处理所有其他未捕获的异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("全局异常捕获", e);
        return Result.error(ResultEnum.INTERNAL_SERVER_ERROR);
    }

}
