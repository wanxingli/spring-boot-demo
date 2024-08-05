package com.sb.demo.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserRole extends CommonBean implements Serializable {

    private Integer userAccount;

    private Integer roleName;

}
