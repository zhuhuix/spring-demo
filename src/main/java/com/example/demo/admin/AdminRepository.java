package com.example.demo.admin;

import org.springframework.data.repository.CrudRepository;

/**
 * 基于SpringMVC框架开发web应用--管理员数据操作层
 *
 * @author zhuhuix
 * @date 2020-07-08
 */
public interface AdminRepository extends CrudRepository<Admin,Long> {

    // 根据用户名查找管理员
    Admin findByUserName(String username);
}
