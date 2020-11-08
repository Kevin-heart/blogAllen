package com.lrm.blog.po;

/**
 * 项目名称：blog
 * 类 名 称：Tag
 * 类 描 述：TODO
 * 创建时间：2020/7/2 4:41 下午
 * 创 建 人：huanghao
 */
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tag标签实体类
 */
@Entity //与数据库建立联系
@Table(name = "t_tag")//指定数据库中的表
public class Tag {

    @Id //指定数据库中的主键
    @GeneratedValue //id的与数据库生成的策越
    //Long类型的主键约束
    private Long id;

    //String字符串类型的名称
    private String name;

    //一个tag包含多个Blog
    @ManyToMany(mappedBy = "tags") //通过Blog的tags与tag进行关系维护
    //在Tag它也是一个数组Blog一个集合 并完成ArrayList初始化
    private List<Blog> blogs = new ArrayList<>();
    /**
     * 空构造方法函数
     */
    public Tag() {

    }

    /**
     * set和get方法
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    /**
     * 重写了Tag的toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
