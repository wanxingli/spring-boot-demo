package com.sb.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserEnum implements UserExceptionAssert {

    CREATE_USER_SUCCESS("U200", "添加用户成功"),
    CREATE_USER_ERROR("U5001", "添加用户失败"),
    USER_NOT_EXIT("U4002", "用户信息不存在")
    ;

    private String code;

    private String message;

    @Override
    public UserException newException(Object... args) {
        return null;
    }

    @Override
    public UserException newException(Throwable t, Object... args) {
        return null;
    }


}
