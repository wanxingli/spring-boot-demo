package com.sb.demo.controller;

import com.sb.demo.bean.CommonResult;
import com.sb.demo.bean.User;
import com.sb.demo.exceptions.UserEnum;
import com.sb.demo.service.IUserService;
import com.sb.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService iuserService;

    private CommonResult<Object> result = new CommonResult<>();

    @PostMapping("create")
    @ResponseBody
    public CommonResult<Object> create(@RequestBody User user) {
        user.setCreateTime(new Date());
        iuserService.createUser(user);
        result.setCode(UserEnum.CREATE_USER_SUCCESS.getCode());
        result.setMessage(UserEnum.CREATE_USER_SUCCESS.getMessage());
        return result;
    }

    @PutMapping("update")
    @ResponseBody
    public Object update(@RequestBody User user) {
        user.setUpdateTime(new Date());
        iuserService.updateUser(user);
        return "success";
    }

    @DeleteMapping("delete/{userAccount}")
    @ResponseBody
    public Object delete(@PathVariable String userAccount) {
        iuserService.delUser(userAccount);
        return "success";
    }

    @GetMapping("select/{userAccount}")
    @ResponseBody
    public Object select(@PathVariable String userAccount) {
        return iuserService.selectUser(userAccount);
    }

    @PostMapping("select/page")
    @ResponseBody
    public Object selectByPage(UserVo userVo) {
        return iuserService.select(userVo);
    }

}
