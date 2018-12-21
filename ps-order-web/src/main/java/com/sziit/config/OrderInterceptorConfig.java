package com.sziit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.config
 *  @文件名:   OrderInterceptorConfig
 *  @创建者:   daizengyi9.0
 *  @创建时间:  2018/12/21 16:29
 *  @描述：    TODO
 */
@Component
public class OrderInterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private OrderInterceptor orderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加一个拦截器
        registry.addInterceptor(orderInterceptor).addPathPatterns("/order/**");
    }
}
