package com.sb.demo.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Permission extends CommonBean implements Serializable {

    private String permissionName;

    private String permissionType;

    private String permissionDesc;

}
