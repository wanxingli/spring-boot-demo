package com.sb.demo.exceptions;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private String errorCode;

    private Object[] params;

    public UserException() {
        super();
    }

    public UserException(String errorCode) {
        super(errorCode);
    }

    public UserException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public UserException(String errorCode, Object[] params) {
        this(errorCode);
        this.params = params;
    }

    public UserException(UserExceptionAssert userExceptionAssert, String errorCode, Object... args) {
        this(errorCode);
        System.out.println("============");
        this.params = args;
        System.out.println(userExceptionAssert.getMessage());
    }

}
