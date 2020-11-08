package com.lrm.blog.service;

/**
 * 项目名称：blog
 * 类 名 称：UserSerice
 * 类 描 述：TODO
 * 创建时间：2020/7/2 8:18 下午
 * 创 建 人：huanghao
 */

import com.lrm.blog.po.User;

/**
 * 业务逻辑处理层（Service）
 * 接口
 */
public interface UserService {

    /**
     * 关于用户登录 有一个检查用户登录和密码的方法
     */
    //1:定义根据用户名和密码检查接口
    User checkUser(String username,String password);


}
