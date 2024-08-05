package com.sb.demo.test;

import com.sb.demo.Application;
import com.sb.demo.bean.User;
import com.sb.demo.dao.UserMapper;
import com.sb.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class TestDemo {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testUser() {
        UserVo user = new UserVo();
        user.setUserAccount("liwanxing");
        List<User> userList = userMapper.select(user);
        log.info("userInfo: {}", userList);
        userList.stream().forEach((userInfo) -> log.info("username: {}", userInfo.getUsername()));
    }

    @Test
    public void testRides() {
        boolean bool = redisTemplate.hasKey("123");
        log.info("bool: {}", bool);
    }
}
