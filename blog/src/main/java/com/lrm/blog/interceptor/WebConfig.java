package com.lrm.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 项目名称：blog
 * 类 名 称：WebConfig
 * 类 描 述：TODO
 * 创建时间：2020/7/3 3:24 下午
 * 创 建 人：huanghao
 */

/**
 * 配置类拦截器
 */
@Configuration //这个注解需要springboot扫描组件到这个类的配置
public class WebConfig extends WebMvcConfigurerAdapter {

    //1:重写这个addInterceptors这个方法把我我定义的过滤器的网加载进来
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //调用addInterceptors的方法加载匹配
        registry.addInterceptor(new LoginInterceptor())
                //加载匹配要过滤哪些路径 admin/下的所有路径
                .addPathPatterns("/admin/**")
                //调用这个方法要排除的路径
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
