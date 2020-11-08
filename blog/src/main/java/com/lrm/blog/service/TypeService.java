package com.lrm.blog.service;

/**
 * 项目名称：blog
 * 类 名 称：TypeService
 * 类 描 述：TODO
 * 创建时间：2020/7/3 4:22 下午
 * 创 建 人：huanghao
 */
import com.lrm.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 分类管理的Service接口层
 */
public interface TypeService  {

    //1:定义实体对象新增需要保存返回一个Type
    Type saveType(Type type);

    //2:定义查询方法根据Long类型的id查
    Type getType(Long id);

    /**
     * 分页类型列表
     * @param pageable
     * @return
     */
    //3:分页查询列表的形式采用List
    Page<Type> listType(Pageable pageable);

    /**
     * 更新类型
     * @param id
     * @param type
     * @return
     */
    //4:修改的数据传过来两个参数根据id先查询实体的对象,然后把对象按照新的内容进行修改,然后保存到数据库
    Type updateType(Long id,Type type);//Type就是修改参数的Type

    /**
     * 删除类型根据id
     * @param id
     */
    //5:删除返回一个空值null根据主键来删除Long类型的id
    void deleteType(Long id);

    //6:定义方法通过一个名称查询一个Type
    Type getTypeByName(String name);

    List<Type> listType();
}
