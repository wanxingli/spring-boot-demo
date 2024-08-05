package com.sb.demo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "demoFilter")
@Slf4j
public class DemoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Start search user info from redis.");
        // 用户登录时将用户信息（token）存入redis， 根据token获取用户信息
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
