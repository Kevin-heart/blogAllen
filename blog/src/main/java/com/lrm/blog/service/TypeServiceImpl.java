package com.lrm.blog.service;

import com.lrm.blog.NotFoundException;
import com.lrm.blog.dao.TypeRepository;
import com.lrm.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 项目名称：blog
 * 类 名 称：TypeServiceImpl
 * 类 描 述：TODO
 * 创建时间：2020/7/3 4:34 下午
 * 创 建 人：huanghao
 */

/**
 * Type接口实现处理类
 */
@Service //获取Service扫描
public class TypeServiceImpl implements TypeService {

    @Autowired //注入分类的操作Mysql的dao的持久层
    private TypeRepository typeRepository;

    @Transactional //数据库访问操作事务增删改查都要放到事物里面进行相关操作
    @Override
    //保存直接调用typeRepository 值传递type
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional//增删改查
    @Override //查询
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional//增删改查
    @Override //分页查询
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional//增删改查
    @Override
    public Type updateType(Long id, Type type) {
        //根据id查到地址 更新根据id查询到一个对象
        Type t = typeRepository.getOne(id);
        //判断type没有值的话那么我们就抛出异常
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        //调用该方法传递type值赋值t对象里面
        BeanUtils.copyProperties(type, t);

        //返回值 调用save方法t对象传递有id所有它就会就行更新修改动作
        return typeRepository.save(t);
    }

    @Transactional//增删改查
    @Override //删除操作
    public void deleteType(Long id) {
        //根据id删除数据
        typeRepository.deleteById(id);
    }

    //实现这个Type新增校验的方法
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }


}
