package com.example.demo.register;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mybatis数据层
 *
 * @author zhuhuix
 * @date 2020-07-23
 */
@Repository
public interface UserMapper {

    // 查找所有用户
    List<User> findAll();

    // 根据id查找用户
    User findById(Long id);

    // 新增用户
    Long addUser(User user);

    // 更新用户
    void updateUser(User user);

    // 删除用户
    void deleteById(Long id);

    // 自定义添加通过用户名称查找用户信息
    List<User> findByName(String name);
}
