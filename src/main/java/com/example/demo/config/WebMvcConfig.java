package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by linziyu on 2019/2/9.
 * <p>
 * 视图配置类
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/violation").setViewName("violation");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/register_page").setViewName("register");
        registry.addViewController("/updateViolation").setViewName("update_violation");
        registry.addViewController("/change_user_role").setViewName("change_user_role");
        registry.addViewController("/display_dynamic_info").setViewName("dynamic");
        registry.addViewController("/register_page").setViewName("register");
//
//
    }
//
}
