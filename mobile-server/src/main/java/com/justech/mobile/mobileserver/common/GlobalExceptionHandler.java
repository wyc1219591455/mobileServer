package com.justech.mobile.mobileserver.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobalExceptionHandler
 * @Author: xiaofeng.yang
 * @Date: Create in 10:12 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
//@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity exceptionHandler(HttpServletRequest request,Exception ex){
        HttpStatus httpStatus=getStatus(request);
        return new ResponseEntity(httpStatus);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity runTimeExceptionHandler(HttpServletRequest request,Exception ex){
        HttpStatus httpStatus=getStatus(request);
        return new ResponseEntity(httpStatus);
    }
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }


}
