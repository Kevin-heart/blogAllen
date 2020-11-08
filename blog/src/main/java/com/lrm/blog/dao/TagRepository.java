package com.lrm.blog.dao;

import com.lrm.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * 类 描 述：TODD
 * 项目名称：blog
 * 类 名 称：TagRepository
 * 创建时间：2020/7/21 3:31 PM
 * 创 建 人：huanghao
 *
 * @version: V2.2
 */
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

}
