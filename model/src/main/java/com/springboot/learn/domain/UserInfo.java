package com.springboot.learn.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 *  封装服务请求和响应。拷贝原因是让每个的类职责单一
 */
@Data
public class UserInfo
{

    private Long id;

    private String name;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

    public UserInfo() {
    }

   
}
