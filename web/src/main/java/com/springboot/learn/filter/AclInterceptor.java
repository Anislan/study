package com.springboot.learn.filter;

import com.springboot.learn.domain.User;
import com.springboot.learn.domain.UserInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AclInterceptor extends HandlerInterceptorAdapter {

    /**
     *  该请求类不做权限验证
     */
    private String [] peritUrls = new String[]{"/users/login"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;

        //
        if(!ArrayUtils.contains(peritUrls,request.getRequestURI())){
            UserInfo user = (UserInfo) request.getSession().getAttribute("user");

            // 用不用身份验证，身份验证过了，有没有权限
            if (user == null) {
                response.setContentType("text/plain");
                response.getWriter().write("need authentication");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                result = false;
            }else{
                String method = request.getMethod();

                if(!user.hasPermission(method,user.getPermissions())){
                    response.setContentType("text/plain");
                    response.getWriter().write("forbidden");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    result = false;
                }
            }
        }



        return result;
    }
}
