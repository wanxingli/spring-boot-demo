package com.sb.demo.dao;

import com.sb.demo.bean.RolePermission;
import java.util.List;

public interface RolePermissionMapper {

    int create(RolePermission userRole);

    int update(RolePermission userRole);

    int del(Integer id);

    RolePermission selectRolePermission(Integer id);

    List<RolePermission> select(RolePermission userVo);
}
