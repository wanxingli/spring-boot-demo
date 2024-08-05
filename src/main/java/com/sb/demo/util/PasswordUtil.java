package com.sb.demo.util;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.lang.Nullable;
import sun.misc.BASE64Encoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class PasswordUtil {

    /**
     * 把字符串编码成base64格式
     * @param password 字符串明文密码
     * @return String
     */
    public static String base64Encode(@Nullable String password) {
        if (StringUtils.isBlank(password))
            throw new IllegalArgumentException(password);
        return new BASE64Encoder().encode(password.trim().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 判断字符串是否为base64编码
     * @param str 字符串
     * @return Boolean
     */
    public static Boolean isBase64(String str) {
        if (StringUtils.isBlank(str))
            throw new IllegalArgumentException("判断字符串是否为base64编码时，被判断的字符串不能为空");
        String pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(pattern, str);
    }

    public static void main(String[] args) {
        String password = "wear";
        String password1 = base64Encode(password);
        System.out.println(password1);
        Boolean password2 = isBase64(password1);
        System.out.println(password2);
    }
}
