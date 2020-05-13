package com.springboot.learn.filter;

import com.springboot.learn.domain.AuditLog;
import com.springboot.learn.domain.User;
import com.springboot.learn.domain.UserInfo;
import com.springboot.learn.reprository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuditLogInterceptor extends HandlerInterceptorAdapter{


    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuditLog log = new AuditLog();
        log.setMethod(request.getMethod());
        log.setPath(request.getRequestURI());
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if (user != null) {
            log.setUserName(user.getUserName());
        }
        auditLogRepository.save(log);
        request.setAttribute("auditLogId",log.getId());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
;
        Long auditLogId= (Long) request.getAttribute("auditLogId");

        AuditLog log = auditLogRepository.findById(auditLogId).get();

        log.setStatus(response.getStatus());

        auditLogRepository.save(log);
    }
}
