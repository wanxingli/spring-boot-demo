package com.sb.demo.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Company extends CommonBean implements Serializable {

    private String companyName;

    private String companyStatus;

    private String companyAccount;

    private String password;

    private Integer companyUserCount;

    private Date lockTime;

    private String companyAddress;

    private String companyLinkman;

    private String companyPhone;
}
