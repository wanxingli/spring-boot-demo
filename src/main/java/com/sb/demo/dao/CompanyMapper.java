package com.sb.demo.dao;

import com.sb.demo.bean.Company;

import java.util.List;

public interface CompanyMapper {

    int create(Company company);

    int update(Company company);

    int del(String companyAccount);

    Company selectCompany(String companyAccount);

    List<Company> select(Company company);
}
