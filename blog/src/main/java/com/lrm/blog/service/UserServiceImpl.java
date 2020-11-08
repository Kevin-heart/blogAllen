package com.lrm.blog.service;

import com.lrm.blog.dao.UserRepository;
import com.lrm.blog.po.User;
import com.lrm.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：blog
 * 类 名 称：UserSericeImpl
 * 类 描 述：TODO
 * 创建时间：2020/7/2 8:23 下午
 * 创 建 人：huanghao
 */

/**
 * User用户接口实现定义的UserService
 */
@Service
public class UserServiceImpl implements UserService {

    //定义好需要处理逻辑拿到UserService
    // 用户名密码查一下数据库能不能根据用户名密码查到相对应记录查到返回user查不到返回空null值

    @Autowired //通过这个注解把实现UserRepoitory的方法注入进来
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
