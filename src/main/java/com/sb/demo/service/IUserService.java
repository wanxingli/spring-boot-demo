package com.sb.demo.service;

import com.github.pagehelper.PageInfo;
import com.sb.demo.bean.User;
import com.sb.demo.vo.UserVo;
import java.util.List;

public interface IUserService {

    int createUser(User user);

    int updateUser(User user);

    int delUser(String userAccount);

    User selectUser(String userAccount);

    PageInfo<List<User>> select(UserVo userVo);
}
