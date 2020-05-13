package com.springboot.learn.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 *  用户模型
 */
@Data
@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     *  数据库层面校验
     *  @NotBlank是应用层面，在sql执行前校验，不管数据库层面是否为空
     *  nullable 是sql执行时校验
     */
    @NotBlank(message = "用户名不能为空")
    @Column(unique = true,name = "user_name",table = "user",nullable = true)
    private String userName;

    private String password;

    private String permissions;


    public User() {
    }

    public UserInfo buildInfo(){
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(this,userInfo);
        return userInfo;
    }



}
