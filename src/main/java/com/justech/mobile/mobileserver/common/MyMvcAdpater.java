package com.justech.mobile.mobileserver.common;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyMvcAdpater
 * @Author: xiaofeng.yang
 * @Date: Create in 18:46 2019/7/31
 * @Description : TODO
 * @Version: 1.0
 */
@Configuration
public class MyMvcAdpater implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/","classpath:/templates/");
    }
}
