package com.cat.recruit.common.exception;

import com.cat.recruit.common.result.Result;
import com.cat.recruit.common.result.ResultEnum;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    /**
     * 处理所有异常
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("全局异常捕获", e);
        return Result.error(ResultEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理JWT异常
     * @param e JWT异常
     * @return JWT异常结果
     */
    @ExceptionHandler(JwtException.class)
    public Result handleJwtException(JwtException e) {
        log.error("JWT异常捕获", e);
        return Result.error(ResultEnum.UNAUTHORIZED);
    }

    /**
     * 处理参数错误异常
     * @param e 参数错误异常
     * @return 参数错误结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("参数错误异常捕获", e);
        return Result.error(ResultEnum.BAD_REQUEST);
    }

    /**
     * 处理业务异常
     * @param e 业务异常
     * @return 业务异常结果
     */
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        log.error("业务异常捕获", e);
        return Result.error(e);
    }

}
