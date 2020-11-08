package com.lrm.blog.handler;

/**
 * 项目名称：blog
 * 类 名 称：ControllerExceptionHanlder
 * 类 描 述：TODO
 * 创建时间：2020/7/1 8:05 下午
 * 创 建 人：huanghao
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * 拦截器
 */

@ControllerAdvice //拦截所有
public class ControllerExceptionHanlder {
    //记录异常拿到这个对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class) //通过这个注解标识这个方法做异常注解的
    //处理异常信息从requset来获取访问的url暴露异常
    public ModelAndView exceptionHanlder(HttpServletRequest request,Exception e) throws Exception {

        //处理业务逻辑
        logger.error("Request URL : {}, Exception : {}",request.getRequestURI(),e);

        if(AnnotationUtils.findAnnotation(
           e.getClass(), ResponseStatus.class) !=null){
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURI());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        //返回值这个错误对象
        return mv;
    }
}
