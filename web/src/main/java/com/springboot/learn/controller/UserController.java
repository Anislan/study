package com.springboot.learn.controller;

import com.springboot.learn.domain.User;
import com.springboot.learn.domain.UserInfo;
import com.springboot.learn.reprository.IUserRepository;
import com.springboot.learn.reprository.UserRepository;
import com.springboot.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public void login(@Validated UserInfo userInfo,HttpServletRequest request){
        UserInfo info = userService.login(userInfo);
        // 调用getSession方法，在服务端生成一个sessionId
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.getSession(true).setAttribute("user",info);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();;
    }

    /**
     *  接口层面数据校验
     * @param userInfo
     * @return
     */
    @PostMapping
    public UserInfo create(@RequestBody @Validated UserInfo userInfo){
//        User user = new User();
//        user.setName(name);
//        if(userRepository.save(user)){
//            System.out.printf("用户对象: %s 保存成功! \n ", user);
//        };
//
        return userService.create(userInfo);
    }

    @PutMapping("/{id}")
    public UserInfo update(@RequestBody UserInfo user){
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserInfo get(@PathVariable Long id, HttpServletRequest request){
        // 用户来获取用户信息
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if (user == null || !user.getId().equals(id)) {
            throw new RuntimeException("身份认真信息异常，获取用户信息失败");
        }
        return userService.get(id);
    }

    @GetMapping
    public List<UserInfo> query(String name){
        return  userService.query(name);
        //        String sql = "SELECT id,username FROM homepage_user where username = '"+name+"'";
//        List datas = jdbcTemplate.queryForList(sql);
//        return iUserRepository.findByName(name);
    }

}
