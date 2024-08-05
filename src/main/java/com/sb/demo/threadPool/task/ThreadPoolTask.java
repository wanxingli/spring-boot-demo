package com.sb.demo.threadPool.task;

import com.sb.demo.bean.LdapUser;
import com.sb.demo.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Slf4j
@Component
public class ThreadPoolTask {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Async("taskAsyncPool")
    public void threadTask () {
//        LdapQuery query = query().where("sAMAccountName").is(loginName);
        /*ContextSource contextSource = ldapTemplate.getContextSource();
        log.info("contextSource: {}", contextSource);
        String dn = "uid=liwanxing,dc=example,dc=org";*/
        /*"userAccount":"liwanxing",
         * "username":"liwanxing","password":"123456","state":"1","lockTime":"2023-11-17 24:00:00",
         * "email":"scott_l@163.com","phone":"17682890306","companyAccount":"zdjx"
         *
         * */


        /*List<LdapUser> ldapUsers = ldapTemplate.findAll(LdapUser.class);
        System.out.println(ldapUsers);
        LdapUser user = new LdapUser();

        Attribute attribute = new BasicAttribute("objectClass");
        attribute.add("top");
        attribute.add("person");
        attribute.add("organizationalPerson");
        attribute.add("inetOrgPerson");

        Attributes attributes = new BasicAttributes();
        attributes.put(attribute);
        // 用户登录名
        attributes.put("userPrincipalName", "liwanxing");
        attributes.put("sAMAccountName", "liwanxing");
        attributes.put("cn", "liwanxing");
        attributes.put("sn", "li");
        attributes.put("givenname", "liwanxing");
        attributes.put("displayName", "李万兴");
        attributes.put("mail", "scott_l@163.com");
        attributes.put("pwdLastSet", "0");
        attributes.put("userAccountControl", "544");
        attributes.put("userPassword", "123456");*/

        LdapUser ldapUser = new LdapUser();
        Name id = LdapNameBuilder.newInstance()
                .add("ou", "zdjx")
                .add("cn", "liwanxing")
                .build();
        ldapUser.setId(id);
        ldapUser.setDistinguishedName("liwanxing");
        ldapUser.setLoginName("liwanxing");
        ldapUser.setGivenName("liwanxing");
        ldapUser.setEmail("scott_l@163.com");
        ldapUser.setDisplayName("李万兴");
        ldapUser.setUserAccountControl("544");
        ldapUser.setSn("li");
        ldapUser.setUserName("liwanxing");

        ldapTemplate.create(ldapUser);


        //ldapTemplate.bind(LdapNameBuilder.newInstance().add("CN", "liwanxing").build(), null, attributes);

        /*String keyWord = "*liwanxing*";
        LdapQuery query = query().where("sAMAccountName").like(keyWord).or("cn").like(keyWord)
                .or("mail").like(keyWord);
        List<LdapUser> ldapUserList = ldapTemplate.find(query, LdapUser.class);
        log.info("ldapUserList: {}", ldapUserList);*/

        /*ldapTemplate.create(user);
        ldapTemplate.bind(dn, user, attributes);
        ldapTemplate.lookup("liwanxing");*/



    }
}
