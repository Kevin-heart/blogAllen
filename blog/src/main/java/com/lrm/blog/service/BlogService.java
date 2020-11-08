package com.lrm.blog.service;

/**
 * 项 目 名 称：${project_name}
 * 类 名 称：${type_name}
 * 创 建 人：${user}
 * 创建时间：${date} ${time}
 * 类 描 述：
 *
 * @version: V1.0
 */

import com.lrm.blog.po.Blog;
import com.lrm.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Blog Service接口
 */
public interface BlogService {

    /**
     * 查询方法
     *
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 分页查询 返回 page 对象
     *
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);


    /**
     * 新增 blog
     *
     * @param blog
     * @return 传递 blog 对象
     */
    Blog saveBlog(Blog blog);

    /**
     * 更新 blog
     *
     * @param blog
     * @return
     */
    Blog updateBlog(Long id, Blog blog);

    /**
     * 删除 blog 根据主键来删除
     *
     * @return
     */

    void deleteBlog(Long id);

}
