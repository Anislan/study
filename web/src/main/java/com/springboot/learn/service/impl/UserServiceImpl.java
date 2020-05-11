package com.springboot.learn.service.impl;

import com.lambdaworks.crypto.SCryptUtil;
import com.springboot.learn.domain.User;
import com.springboot.learn.domain.UserInfo;
import com.springboot.learn.reprository.IUserRepository;
import com.springboot.learn.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private IUserRepository iUserRepository;

    @Autowired
    public UserServiceImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public UserInfo create(UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo,user);
        // 使用工具加密
        user.setPassword(SCryptUtil.scrypt(user.getPassword(),32768,8,1));
        iUserRepository.save(user);
        userInfo.setId(user.getId());
        return userInfo;
    }

    @Override
    public UserInfo update(UserInfo user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserInfo get(Long id) {
        return iUserRepository.findById(id).get().buildInfo();
    }

    @Override
    public List<UserInfo> query(String name) {
        return null;
    }
}
