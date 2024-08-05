package com.sb.demo.exceptions;

public interface Assert {

    /**
     * 创建异常
     * @param obj
     * @return UserException
     */
    UserException newException(Object... obj);

    /**
     * 创建异常
     * @param t
     * @param args
     * @return UserException
     */
    UserException newException(Throwable t, Object... args);

    /**
     * 断言对象obj非空。如果对象obj为空，则抛出异常
     *
     * @param obj 待判断对象
     */
    default void assertNotNull(Object obj) throws UserException {
        if (obj == null) {
            throw newException(obj);
        }
    }

    /**
     * 断言对象obj非空。如果对象obj为空，则抛出异常
     * 异常信息message支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param obj 待判断对象
     * @param args message占位符对应的参数列表
     */
    default void assertNotNull(Object obj, Object... args) throws UserException {
        if (obj == null) {
            throw newException(args);
        }
    }
}
