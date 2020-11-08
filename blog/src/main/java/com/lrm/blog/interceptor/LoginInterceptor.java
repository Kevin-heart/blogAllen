package com.lrm.blog.interceptor;

/**
 * 项目名称：blog
 * 类 名 称：LoginInterceptor
 * 类 描 述：TODO
 * 创建时间：2020/7/3 2:46 下午
 * 创 建 人：huanghao
 */

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 预处理通过这个方法继承springframework Hand 需要重写它类里面的方法
     * 在这个请求会到达目的地之前呢需要预处理一把拦截admin/之后的地址
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //判断这个里面的user拿到为null(未登录)用户登录的地址之后需要重定向返回登录页面
        if (request.getSession().getAttribute("user") ==null){
            //未登录通过重定向到我们的admin
            response.sendRedirect("/admin");

            //不让它下面代码继续执行如false
            return false;

        }
        //如果session为有值的话拿到值的话不等于null的话那么为true重定向跳转的admin登录页面
        return true;
    }
}
