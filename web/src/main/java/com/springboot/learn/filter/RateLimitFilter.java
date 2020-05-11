package com.springboot.learn.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 保证过滤器的逻辑不会被多次执行.实现Filter接口的类，会自动加到Spring过滤器链中
 * order1 流控在前面，越小排的越前
 */
@Component
@Order(1)
public class RateLimitFilter extends OncePerRequestFilter {

    // 每秒钟一个请求过去
    private RateLimiter rateLimiter = RateLimiter.create(1);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 还可以处理http请求，就执行接下来过滤
        if (rateLimiter.tryAcquire()) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else{
            // 当请求超过设定的值后，设置错误返回码
            httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpServletResponse.getWriter().write("too many request!!!");
        }
    }
}
