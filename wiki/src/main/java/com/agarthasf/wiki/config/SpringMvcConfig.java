package com.agarthasf.wiki.config;

import com.agarthasf.wiki.interceptor.LogInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.annotation.Resource;

@Component
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LogInterceptor logInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }



}
