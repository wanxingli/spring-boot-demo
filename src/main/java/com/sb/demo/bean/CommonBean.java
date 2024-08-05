package com.sb.demo.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CommonBean {

    private Integer id;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;
}
