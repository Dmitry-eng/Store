package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@EnableWebMvc
@Configuration
//@ComponentScan("java")
public class SpringConfig implements WebMvcConfigurer {
//    @Bean
//    public ViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver bean = new InternalResourceViewResolver();
//        bean.setViewClass(JstlView.class);
//        bean.setPrefix("/resources/templates");
//        bean.setSuffix(".html");
//        return bean;
//    }
//@Bean
//public MultipartResolver multipartResolver() {
//    return new CommonsMultipartResolver();
//}
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/img/**")
            .addResourceLocations("file://" + "/");
    registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/");
}
}
