package com.springboot.learn.service;

import com.springboot.learn.domain.User;
import com.springboot.learn.domain.UserInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserService {


    public UserInfo create( UserInfo userInfo);


    public UserInfo update( UserInfo user);


    public void delete( Long id);


    public UserInfo get(Long id);


    public List<UserInfo> query(String name);

}
