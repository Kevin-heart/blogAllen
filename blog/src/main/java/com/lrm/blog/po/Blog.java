package com.lrm.blog.po;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目名称：blog
 * 类 名 称：Blog
 * 类 描 述：TODO
 * 创建时间：2020/7/2 4:07 下午
 * 创 建 人：huanghao
 */

/**
 * 实体类Blog
 */
@Entity //这个注解是连接数据库与Mysql交互的对应生成的能力
@Table(name = "t_blog") //指定数据库对应的表名字
public class Blog {

    @Id //指定是数据库的主键
    @GeneratedValue //默认选择Mysql自动生成
    //long类型的id代表着对应的主键
    private Long id;

    //String字符串类型title标题
    private String title;

    @Basic(fetch = FetchType.LAZY) //通过这种方式使我们的博客上传达到最大化
    @Lob
    //String字符串类型对应的博客的内容
    private String content;

    //String字符串类型的对应博客的首图
    private String firstPicture;

    //String字符串类型的对应博客的标记
    private String flag;

    //String字符串类型的对应着博客浏览次数
    private Integer views;

    //boolean类型的对应着博客赞赏开启
    private boolean appreciation;

    //boolean类型的对应着博客的版权声明开启
    private boolean shareStatement;

    //boolean类型的对应着博客评论开启
    private boolean commentabled;

    //boolean类型的对应着博客的文章发布
    private boolean published;

    //boolean类型对应着博客是否推荐
    private boolean recommend;

    @Temporal(TemporalType.TIMESTAMP)//这里面可以选择数据库中的时间让它对应的数据表生成的时间类型
    //Date类型的对应的博客的创建时间
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    //Date类型的对应的博客更新时间
    private Date updateTime;

    /**
     * Blog只有一个类型对象定义Type
     */
    @ManyToOne //Many一端作为关系维护端
    private Type type;


    @ManyToMany(cascade = {CascadeType.PERSIST})//当我需要新增一个博客页面连同这个博客新的标签新增链锯  即连设置新增标签拿到tags对象同样也会保存到数据库
    //在Blog里面实现Tag标签 List集合并完成初始化ArraysList
    private List<Tag> tags = new ArrayList<>();

    /**
     * ManyToOne对应多对一 关系的维护方
     */
    @ManyToOne//在Blog这一端对应一个User
    //在Blog里只能有一个User用户
    private User user;

    @OneToMany(mappedBy = "blog")//在Blog一对多的Comment并完成初始化
    private List<Comment> comments = new ArrayList<>();

    //定义 getTagId 的属性
    @Transient //不会进入数据库的操作 它只是一个正常的属性值
    private String tagIds;

    //空构造方法
    public  Blog(){

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPulished() {
        return published;
    }

    public void setPulished(boolean pulished) {
        this.published = pulished;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    /**
     *  构造方法函数
     * @param id
     * @param title
     * @param content
     * @param firstPicture
     * @param flag
     * @param views
     * @param appreciation
     * @param shareStatement
     * @param commentabled
     * @param pulished
     * @param recommend
     * @param createTime
     * @param updateTime
     * @param type
     */
    public Blog(Long id, String title, String content, String firstPicture, String flag, Integer views, boolean appreciation, boolean shareStatement, boolean commentabled, boolean pulished, boolean recommend, Date createTime, Date updateTime, Type type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.views = views;
        this.appreciation = appreciation;
        this.shareStatement = shareStatement;
        this.commentabled = commentabled;
        this.published = pulished;
        this.recommend = recommend;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.type = type;
    }

    /**
     * 返回toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", pulished=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
