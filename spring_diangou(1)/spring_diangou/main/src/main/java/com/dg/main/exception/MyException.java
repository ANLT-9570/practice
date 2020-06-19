package com.dg.main.exception;

import com.dg.main.util.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyException {

    @ExceptionHandler(value = AuthorizationException.class)//AuthorizationExceptionAuthorizationException
    public Result error(){
        return Result.failureResult("404","您暂未拥有该权限");
    }
}
