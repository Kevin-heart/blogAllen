package com.lrm.blog.service;

import com.lrm.blog.NotFoundException;
import com.lrm.blog.dao.BlogRepository;
import com.lrm.blog.po.Blog;
import com.lrm.blog.po.Type;
import com.lrm.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项 目 名 称：${project_name}
 * 类 名 称：${type_name}
 * 创 建 人：${user}
 * 创建时间：${date} ${time}
 * 类 描 述：
 *
 * @version: V1.0
 */
@Service
public class BlogServiceImpl implements BlogService {

    //注入Blog 操作数据库层
    @Autowired
    private BlogRepository blogRepository;

    //根据主键查询到 blog 的 id 查询到 一个对象并返回
    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }


    /**
     * 实现分类动态查询的方法
     * 动态查询 是根据条件来查
     * @param pageable
     * @param blog
     * @return
     */
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        //根据方法拿到查询条件
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //1:方法
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like
                            (root.<String>get("title"),"%" +blog.getTitle()));
                }

                //2:方法
                if (blog.getTypeId()!= null){
                    predicates.add(cb.equal
                            (root.<Type>get("Type").get("id"),blog.getTypeId()));
                }

                //3:方法
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                //4:查询where 方法相当我们的 sql 语句的条件传参(条件的数组:对象的 size 长度)
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                //5:自动组合我们的条件自动去拼接好动态查询的 sql 语句
                return null;
            }
        },pageable);
    }


    /**
     * 保存一个新的
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        //日期
        blog.setCreateTime(new Date());
        //更新操作
        blog.setUpdateTime(new Date());
        //初始化的时候给它默认为 0  浏览次数
        blog.setViews(0);
        return blogRepository.save(blog);
    }

    /**
     * 新增blog
     * @param id
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if (b == null) {
            throw new NotFoundException("该博客不存在");
        }
        //把属性的 blog 的值赋值给 b对象
        BeanUtils.copyProperties(b,blog);
        return blogRepository.save(b);
    }

    /**
     * 删除 blog 方法
     * @param id
     * @return
     */
    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
