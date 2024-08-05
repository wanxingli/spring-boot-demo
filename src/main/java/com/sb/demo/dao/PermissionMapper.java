package com.sb.demo.dao;

import com.sb.demo.bean.Permission;

import java.util.List;

public interface PermissionMapper {

    int create(Permission permission);

    int update(Permission permission);

    int del(String permissionName);

    Permission selectPermission(String permissionName);

    List<Permission> select(Permission permission);
}
