package com.springboot.learn.filter;

import com.lambdaworks.crypto.SCryptUtil;
import com.springboot.learn.domain.User;
import com.springboot.learn.reprository.IUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
@Order(2)
public class BasicAuthecationFilter extends OncePerRequestFilter{

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authHeader)) {
            String token64 = StringUtils.substringAfter(authHeader,"Basic ");
            String token = new String(Base64Utils.decodeFromString(token64));
            String [] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(token,":");

            String username = items[0];
            String password= items[1];

            User user = iUserRepository.findByUserName(username);

//            if (user != null && StringUtils.equals(password,user.getPassword())) {
            if (user != null && SCryptUtil.check(password,user.getPassword())) {

                request.setAttribute("user",user);
            }
        }

        filterChain.doFilter(request,response);
    }
}
