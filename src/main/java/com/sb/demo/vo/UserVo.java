package com.sb.demo.vo;

import com.sb.demo.bean.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserVo extends User {

    private Integer pageSize;

    private Integer pageNumber;
}
