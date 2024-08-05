package com.sb.demo.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RolePermission extends CommonBean implements Serializable {

    private Integer roleName;

    private Integer permissionName;
}
