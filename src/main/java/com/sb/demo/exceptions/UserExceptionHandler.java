package com.sb.demo.exceptions;

import com.sb.demo.bean.CommonResult;
import com.sb.demo.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class UserExceptionHandler {

    private CommonResult<Object> result = new CommonResult<>();

    @ExceptionHandler(value = UserException.class)
    public CommonResult<Object> exceptionHandler(UserException e) {
        log.error("Exception: {}", e);
        Assert.notNull(new User(), "user object is null");
        return result;
    }

}
