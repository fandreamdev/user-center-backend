package com.fandream.usercenter.exception;

import com.fandream.usercenter.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * CustomExceptionHandler
 *
 * @author fandream
 * @date 2026-03-14 16:06:26
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(MyBizException.class)
    public <T> BaseResponse<T> myExceptionHandler(MyBizException e) {
        log.error("error:", e);
        return BaseResponse.error(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public <T> BaseResponse<T> otherExceptionHandler(RuntimeException e) {
        log.error("error:", e);
        return BaseResponse.error("系统错误，请稍后重试！");
    }
}
