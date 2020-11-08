package com.lrm.blog.po;

/**
 * 项目名称：blog
 * 类 名 称：Comment
 * 类 描 述：TODO
 * 创建时间：2020/7/2 4:47 下午
 * 创 建 人：huanghao
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论的实体类
 */
@Entity //与数据库建立联系
@Table(name = "t_comment")//指定数据库当中的表
public class Comment {

    @Id //指定数据库的主键id
    @GeneratedValue //id生成数据库的策越
    //Long类型的主键id
     private Long id;

     //String字符串类型对应博客的昵称
    private String nickname;

    //String字符串类型的对应博客邮箱
    private String email;

    //String字符串类型对应博客头像
    private String content;

    //String字符串类型对应博客评论内容
    private String avatar;

    @Temporal(TemporalType.TIMESTAMP)//这里面可以选择数据库中的时间让它对应的数据表生成的时间类型
    //Date类型创建时间
    private Date createTime;

    @ManyToOne//在Comment只能有一个Blog类
    private Blog blog;


    @OneToMany(mappedBy = "parentComment")//一个子类对象对应一个父类就进原则
    //在评论类自关联关系 父类有多个子类对象
    private List<Comment> replyComments = new ArrayList<>();
    @ManyToOne
    //它只有一个父类
    private Comment parentComment;


    /**
     * 空构造方法函数
     */
    public Comment() {

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getUpdatetime() {
        return createTime;
    }

    public void setUpdatetime(Date updatetime) {
        this.createTime = updatetime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    /**
     * 重写了Conment的toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", updatetime=" + createTime +
                '}';
    }
}
