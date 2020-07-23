package com.example.demo.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 基于SpringMVC框架开发web应用--用户restful api
 * 增加、删除、修改、查找用户信息的API交互服务
 *
 * @author zhuhuix
 * @date 2020-07-10
 */
@RestController
@RequestMapping("/user/api")
public class UserRestfulApi {
    @Autowired
    private UserService userService;

    // 增加用户信息
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    // 根据id删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // 根据id修改用户
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    // 根据id查找用户
    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUser(id));
    }
}
