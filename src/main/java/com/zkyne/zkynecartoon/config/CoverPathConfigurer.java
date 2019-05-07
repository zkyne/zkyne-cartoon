package com.zkyne.zkynecartoon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: CoverPathConfigurer
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/22 11:53
 */
@Configuration
public class CoverPathConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/zkyne_cartoon/**").addResourceLocations("file:D:/data/static/zkyne_cartoon/");
    }
}
