package com.example.demo.register;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 基于SpringMVC框架开发web应用--用户类
 *
 * @author zhuhuix
 * @date 2020-07-03
 * @date 2020-07-07 添加JPA映射注解
 */
@Entity
public class User implements Serializable {
    // 用户id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 用户名
    @NotBlank(message = "用户名称不能为空")
    @Column(name="name")
    private String name;
    // 邮箱
    @Column(name="email")
    @Pattern(message ="邮箱格式不符", regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    private String email;

    // 创建时间
    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    // 更新时间
    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

    public User(){}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
