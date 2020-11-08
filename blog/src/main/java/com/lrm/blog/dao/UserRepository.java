package com.lrm.blog.dao;

import com.lrm.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 项目名称：blog
 * 类 名 称：UserRepository
 * 类 描 述：TODO
 * 创建时间：2020/7/2 8:36 下午
 * 创 建 人：huanghao
 */

/**
 * 这个一个持久层（dao）操作数据库Mysql的类 需要构建dao层
 * 数据库操作层次
 */
//这个类是引用SpringBoot的JPA使用Springboot容器的jpa通过继承的方法继承实现jpaRepositry的方法<User用户主键,l类型>
public interface UserRepository extends JpaRepository<User,Long> {

    //定义方法遵循它的命名规则
    //根据findUsernameAndPasswrd方法查询到Mysql
    User findByUsernameAndPassword(String username,String password);
}
