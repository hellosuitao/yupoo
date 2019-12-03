package org.ruixun.yupoo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    配置拦截路径
    作者：李万君
 */
@Configuration
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //将自定义的拦截器加入到拦截器注册表中
        registry.addInterceptor(loginInterceptor).addPathPatterns("/album/addAlbum");
    }

   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler请求路径
        //addResourceLocations 在项目中的资源路径
        //setCacheControl 设置静态资源缓存时间
        registry.addResourceHandler("/static/**")
                //将所有/static/** 访问都映射到classpath:/static/ 目录下
                .addResourceLocations("classpath:/static/");
    }

}
