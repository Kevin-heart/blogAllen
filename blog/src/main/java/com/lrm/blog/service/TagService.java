package com.lrm.blog.service;

import com.lrm.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 类 描 述：TODD
 * 项目名称：blog
 * 类 名 称：TagService
 * 创建时间：2020/7/21 3:27 PM
 * 创 建 人：huanghao
 *
 * @version: V2.2
 */
public interface TagService {

    Tag saveTag(Tag type);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    Tag updateTag(Long id, Tag type);

    void deleteTag(Long id);
}
