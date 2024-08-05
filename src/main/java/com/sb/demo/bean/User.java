package com.sb.demo.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class User extends CommonBean implements Serializable {

    private String userAccount;

    private String username;

    private String password;

    private String state;

    private Date lockTime;

    private String email;

    private String phone;

    private String companyAccount;

    /**
     * curl -X POST -H "Content-Type:application/json;charset=UTF-8" -d '{"userAccount":"liwanxing",
     * "username":"liwanxing","password":"123456","state":"1","lockTime":"2023-11-17 24:00:00","email":"scott_l@163.com",
     * "phone":"17682890306","companyAccount":"zdjx"}' http://localhost:8080/user/create
     *
     * Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot
     * deserialize value of type `java.util.Date` from String "2023-11-17 24:00:00": not a valid representation
     * (error: Failed to parse Date value '2023-11-17 24:00:00': Cannot parse date "2023-11-17 24:00:00": while it seems
     * to fit format 'yyyy-MM-dd'T'HH:mm:ss.SSSX', parsing fails (leniency? null)); nested exception is
     * com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.util.Date`
     * from String "2023-11-17 24:00:00": not a valid representation (error: Failed to parse Date value
     * '2023-11-17 24:00:00': Cannot parse date "2023-11-17 24:00:00": while it seems to fit format
     * 'yyyy-MM-dd'T'HH:mm:ss.SSSX', parsing fails (leniency? null))
     *  at [Source: (PushbackInputStream); line: 1, column: 94] (through reference chain: com.sb.demo.bean.User["lockTime"])]
     */

}
