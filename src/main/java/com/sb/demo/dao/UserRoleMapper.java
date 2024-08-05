package com.sb.demo.dao;

import com.sb.demo.bean.UserRole;

import java.util.List;

public interface UserRoleMapper {

    int create(UserRole userRole);

    int update(UserRole userRole);

    int del(Integer id);

    UserRole selectUserRole(Integer id);

    List<UserRole> select(UserRole userVo);
}
