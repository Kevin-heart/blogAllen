package com.lrm.blog.vo;

/**
 * 项 目 名 称：${project_name}
 * 类 名 称：${type_name}
 * 创 建 人：${user}
 * 创建时间：${date} ${time}
 * 类 描 述：
 *
 * @version: V1.0
 */

/**
 * 查询的对象
 */
public class BlogQuery {

    /**
     * 属性
     */
    //查询String的标题
    private String title;

    //Long 类型的 typeid
    private Long typeId;


    private boolean recommend;

    //无参构造
    public BlogQuery(){

    }

    /**
     * 构造方法
     * @return
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
