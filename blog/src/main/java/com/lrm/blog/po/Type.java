package com.lrm.blog.po;

/**
 * 项目名称：blog
 * 类 名 称：Type
 * 类 描 述：TODO
 * 创建时间：2020/7/2 4:35 下午
 * 创 建 人：huanghao
 */
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Type类型的实体类
 */
@Entity //与数据库建立联系
@Table(name = "t_type")//指定数据库的表
public class Type {

    @Id //指定数据库的主键
    @GeneratedValue//主键的生成策越
    //Long类型的主键
    private Long id;

    //通过注解的方式就行校验参数(说明下面的name不能为空)
    @NotBlank(message = "分类名称不能为空") //type@Valit返回到页面的消息
    //String字符串类型对应着博客的分类的名称
    private String name; //通过分类管理的验证出错的方法机制errors会拿到name然后替换掉

    //OneToMany说明当前Type是被维护Blog主动的维护和Type的关系值端
    @OneToMany(mappedBy = "type") //One一端作为关系被维护端
    //在Type实体类也只有Blog类List集合并进行初始化
    private List<Blog> blogs = new ArrayList<>();

    //空构造函数
    public Type(){

    }
    /**
     * 构造方法函数
     *
     * @param id
     * @param name
     */
    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Type(String name, List<Blog> blogs) {
        this.name = name;
        this.blogs = blogs;
    }

    /**
     * set和get方法
     *
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
     * 重写toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
