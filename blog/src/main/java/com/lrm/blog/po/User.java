package com.lrm.blog.po;

/**
 * 项目名称：blog
 * 类 名 称：User
 * 类 描 述：TODO
 * 创建时间：2020/7/2 5:13 下午
 * 创 建 人：huanghao
 */
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户的实体类构建
 */
@Entity //与数据库建立联系
@Table(name = "t_user")//指定数据中的表
public class User {

    @Id //主键
    @GeneratedValue //生成策越主键
    //主键
    private Long id;
    //昵称
    private String nickname;
    //同户名
    private String username;
    //密码
    private String password;
    //邮箱
    private boolean email;
    //类型
    private String avatar;
    //头像
    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    //创建时间
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    //更新时间
    private Date updateTime;

    @OneToMany(mappedBy = "user")//一对应多个Blog作为被维护方
    private List<Blog> blogs = new ArrayList<>();

    //空构造方法函数
    public User() {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    /**
     * 重写了User用户的toString方法
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email=" + email +
                ", avatar='" + avatar + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
