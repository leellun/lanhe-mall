package com.newland.lanhe.exception;


import com.newland.lanhe.enumeration.ResultCode;
import com.newland.lanhe.model.RestResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;


@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Log4j
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public RestResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息：", e);
        String message = "系统内部异常，异常信息";
        return RestResponse.error(ResultCode.INTERNAL_SERVER_ERROR.getCode(), message);
    }
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public RestResponse handleNotSupportException(Exception e) {
        String message = "不支持该请求类型";
        return RestResponse.error(ResultCode.ERROR.getCode(), message);
    }
    @ExceptionHandler({AccessDeniedException.class})
    public RestResponse handleAccessDeniedException(AccessDeniedException e){
        log.error("未认证，请在前端系统进行认证：", e);
        String message = "未认证，请在前端系统进行认证";
        return RestResponse.error(ResultCode.UN_LOGIN.getCode(), message);
    }

    @ExceptionHandler({BusinessException.class})
    public RestResponse handleBusinessException(BusinessException e) {
        log.error(e.getMessage());
        return RestResponse.error(e.getCode(), e.getMessage());
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public RestResponse handleValidateException(MethodArgumentNotValidException e) {
        return RestResponse.error(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

}
