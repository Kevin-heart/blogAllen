package com.lrm.blog.dao;

/**
 * 项目名称：blog
 * 类 名 称：TypeRepository
 * 类 描 述：TODO
 * 创建时间：2020/7/3 4:38 下午
 * 创 建 人：huanghao
 */

import com.lrm.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 分类管理的
 * 这个一个持久层（dao）操作数据库Mysql的类 需要构建dao层
 * 数据库操作层次
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);

}
