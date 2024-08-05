package com.sb.demo.dao;

import com.sb.demo.bean.User;
import com.sb.demo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int create(User user);

    int update(User user);

    int del(String userAccount);

    User selectUser(String userAccount);

    List<User> select(UserVo userVo);

}
