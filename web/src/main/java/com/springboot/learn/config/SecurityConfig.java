package com.springboot.learn.config;

import com.springboot.learn.filter.AclInterceptor;
import com.springboot.learn.filter.AuditLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

/**
 *  让拦截器生效
 */
@Configuration
@EnableJpaAuditing
public class SecurityConfig implements WebMvcConfigurer{

    @Autowired
    private AuditLogInterceptor auditLogInterceptor;

    @Autowired
    private AclInterceptor aclInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(auditLogInterceptor);
        registry.addInterceptor(aclInterceptor);

    }

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                return Optional.of("jojo");
            }
        };
    }
}
