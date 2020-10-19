package com.server.config;

import com.server.interceptor.SsoInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Slf4j
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private SsoInterceptor ssoInterceptor;

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("{}-过滤器注册成功", SsoInterceptor.class.getName());
        registry.addInterceptor(ssoInterceptor);
    }

}
