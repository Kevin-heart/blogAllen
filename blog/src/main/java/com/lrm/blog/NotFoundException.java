package com.lrm.blog;

/**
 * 项目名称：blog
 * 类 名 称：NotFoundException
 * 类 描 述：TODO
 * 创建时间：2020/7/1 8:47 下午
 * 创 建 人：huanghao
 */

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义的异常类
 */
@Api(description = "自定义异常类")
@ResponseStatus(HttpStatus.NOT_FOUND)//这个状态他这个代表Exception找不到的状态Springboot就会拿到这个这个状态对应到404页面
public class NotFoundException extends RuntimeException {

    //实现构造方法
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
