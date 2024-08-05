package com.sb.demo.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Role extends CommonBean implements Serializable {

    private String roleName;

    private String roleType;

    private String roleDesc;

}
