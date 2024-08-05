package com.sb.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sb.demo.bean.User;
import com.sb.demo.dao.UserMapper;
import com.sb.demo.service.IUserService;
import com.sb.demo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int createUser(User user) {
        return userMapper.create(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delUser(String userAccount) {
        return userMapper.del(userAccount);
    }

    @Override
    public User selectUser(String userAccount) {
        return userMapper.selectUser(userAccount);
    }

    @Override
    public PageInfo<List<User>> select(UserVo userVo) {
        return PageHelper.startPage(userVo.getPageNumber(), userVo.getPageSize()).doSelectPageInfo(() ->
            userMapper.select(userVo)
        );
    }
}
