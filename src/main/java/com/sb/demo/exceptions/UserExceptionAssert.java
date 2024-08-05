package com.sb.demo.exceptions;

public interface UserExceptionAssert extends IResponseEnum, Assert {

    @Override
    default UserException newException(Object... args) {
//        String msg = MessageFormat.format(this.getMessage(), args);
        String msg = "";

        return new UserException(this, msg, args);
    }

    @Override
    default UserException newException(Throwable t, Object... args) {
//        String msg = MessageFormat.format(this.getMessage(), args);
        String msg = "";
        return new UserException(this, msg, t, args);
    }

    @Override
    String getCode();

    @Override
    String getMessage();


}
