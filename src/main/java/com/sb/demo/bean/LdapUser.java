package com.sb.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top"})
@Getter
@Setter
public class LdapUser {

    /**
     * Name dn：相当于唯一标识
     *
     * LDAP中，一个条目(Entry)必须包含一个对象类(objectClass)属性，且需要赋予至少一个值。每一个值将用作一条LDAP条目进行数据存储的模板；
     * 模板中包含了一个条目必须被赋值的属性和可选的属性。
     *
     * objectClass有着严格的等级之分，最顶层是top和alias。例如，organizationalPerson这个objectClass就隶属于person，
     * 而person又隶属于top。
     *
     * objectClass可分为以下3类：
     * 结构型（Structural）：如account、inetOrgPerson、person和organizationUnit；
     * 辅助型（Auxiliary）：如extensibeObject；
     * 抽象型（Abstract）：如top，抽象型的objectClass不能直接使用。
     *
     * 每种objectClass有自己的数据结构，比如我们有一种叫“电话薄”的objectClass，肯定会内置很多属性(attributes)，如姓名(uid)，
     * 身份证号(uidNumber)，单位名称(gid)，家庭地址(homeDirectory)等，这些属性(attributes)中，有些是必填的，
     * 例如，account就要求userid是必填项，而inetOrgPerson则要求cn(common name,常用名称)和sn(sure name,真实名称)是必填项。
     *
     * 原文链接：https://blog.csdn.net/zhuobin_tian/article/details/127209169
     */
    @Id
    @JsonIgnore  // 必写
    private Name id;

    /**
     * 识别名
     */
    @Attribute(name = "distinguishedName")
    private String distinguishedName;

    /**
     * 登录账号
     * required = true
     */
    @Attribute(name = "sAMAccountName")
    private String loginName;

    /**
     * 正式名称，即用户姓，AD域属性值cn，需唯一，例如工号
     */
    @Attribute(name = "cn")
    @DnAttribute(value = "cn", index = 1)
    private String userName;

    /**
     * 姓
     * required = true
     */
    @Attribute(name = "sn")
    private String sn;

    /**
     * 名
     * required = true
     */
    @Attribute(name = "givenName")
    private String givenName;

    /**
     * 显示名称
     * required = true
     */
    @Attribute(name = "displayName")
    private String displayName;

    /**
     * 邮箱
     * required = true
     */
    @Attribute(name = "mail")
    private String email;

    /**
     * 用户属性
     */
    @Attribute(name = "userAccountControl")
    private String userAccountControl;

}
