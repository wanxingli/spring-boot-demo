package com.sb.demo.dao;

import com.sb.demo.bean.Role;

import java.util.List;

public interface RoleMapper {

    int create(Role role);

    int update(Role role);

    int del(String roleName);

    Role selectRole(String roleName);

    List<Role> select(Role role);
}
